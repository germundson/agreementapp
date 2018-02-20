package com.insurance.agreement.utils.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.google.common.collect.Lists;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.insurance.agreement.exceptions.BadRequestException;
import com.insurance.agreement.exceptions.NotAuthorizedException;
import com.insurance.agreement.utils.HttpUtil;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.insurance.agreement.utils.HttpConstants.BAD_REQUEST;
import static com.insurance.agreement.utils.HttpConstants.UNAUTHORIZED;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.http.HttpHeaders.ACCEPT;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.apache.http.HttpStatus.SC_MULTIPLE_CHOICES;
import static org.apache.http.HttpStatus.SC_OK;

@Component
public class HttpUtilImpl implements HttpUtil {

  private final Logger logger = LoggerFactory.getLogger(HttpUtilImpl.class);
  @Value("${application.proxy.enabled}")
  private boolean proxy;
  @Value("${application.proxy.host}")
  private String proxyHost;
  @Value("${application.proxy.port}")
  private int proxyPort;

  @Override
  public HttpResponse execute(HttpRequestBase command) throws IOException {

    return proxy ? clientWithProxy().execute(command) : defaultClient().execute(command);
  }

  private HttpClient defaultClient() {
    return HttpClientBuilder.create().build();
  }

  private HttpClient clientWithProxy() {
    return HttpClientBuilder.create().setProxy(new HttpHost(proxyHost, proxyPort)).build();
  }

  @Override
  public void printResult(HttpResponse response) throws IOException {
    printResult(response, null);
  }

  @Override
  public void printResult(HttpResponse response, Properties properties) throws IOException {

    logger.debug("Response Code : {}"
        , response.getStatusLine().getStatusCode());

    String body = IOUtils.toString(response.getEntity().getContent(), UTF_8);
    if (!"".equalsIgnoreCase(body)) {
      prettyPrintJson(body);
    }

    for (Header header : response.getHeaders("Location")) {
      logger.debug("{}:{}", header.getName(), header.getValue());
      if (properties != null) {
        printResult(execute(buildGet(header.getValue())));
      }
    }
  }

  private void prettyPrintJson(String body) {
    JsonParser jp = new JsonParser();
    JsonElement je = jp.parse(body);
    logger.debug("{}", toGson(body));
  }

  @Override
  public <T> List<T> getListResult(HttpResponse response, Class<T> clazz) throws IOException {

    String json = IOUtils.toString(response.getEntity().getContent(), UTF_8);
    Type type = new ListParameterizedType(clazz);
    return fromGson(json, new TypeReference<List<T>>() {
    });
  }

  private <T> List<T> fromGson(String json, TypeReference<List<T>> typeReference) {
    List<T> result = null;
    try {
      result = defaultMapper().readValue(json, typeReference);
      return result;
    }
    catch (IOException e) {
      logger.warn(e.getMessage(), e);
      return null;
    }
  }

  @Override
  public <T> T getResult(HttpResponse response, Class<T> clazz) throws IOException {
    String json = IOUtils.toString(response.getEntity().getContent(), UTF_8);
    if (response.getStatusLine().getStatusCode() >= SC_OK && response.getStatusLine().getStatusCode() < SC_MULTIPLE_CHOICES) {
      return fromGson(json, clazz);
    }
    prettyPrintJson(json);
    if (response.getStatusLine().getStatusCode() == UNAUTHORIZED) {
      throw new NotAuthorizedException(IOUtils.toString(response.getEntity().getContent(), UTF_8));
    }

    return null;
  }

  @Override
  public <T> T getResult(String json, Class<T> clazz) throws IOException {
    return fromGson(json, clazz);
  }

  @Override
  public HttpGet buildGet(String url) {
    return buildGet(url, null);
  }

  @Override
  public HttpPost buildPost(String url, String body) throws UnsupportedEncodingException {
    return buildPost(url, body, null);
  }

  private HttpPost buildPost(String url, String body, Header[] headers) {
    try {
      HttpPost post = new HttpPost(url);
      post.setEntity(new StringEntity(body));
      post.setHeaders(headers);
      return post;
    }
    catch (UnsupportedEncodingException e) {
      logger.warn(e.getMessage(), e);
      throw new BadRequestException(BAD_REQUEST, "Failed to create a post object", e.getMessage(), e);
    }
  }

  @Override
  public HttpPost buildPost(String url, Object body) throws UnsupportedEncodingException, JsonProcessingException {
    return buildPost(url, toGson(body));
  }

  @Override
  public String toGson(Object object, boolean formated) throws JsonProcessingException {

    ObjectWriter mapper = formated ? defaultMapper().writerWithDefaultPrettyPrinter() : defaultMapper().writer();

    return mapper.writeValueAsString(object);
  }

  private ObjectMapper defaultMapper() {
    return new ObjectMapper()
        .setSerializationInclusion(NON_NULL)
        .setSerializationInclusion(NON_NULL)
        .setDateFormat(new SimpleDateFormat("yyy-MM-dd HH:mm:ss.SSS"))
        .registerModule(new JodaModule())
        .enable(SerializationFeature.INDENT_OUTPUT)
        .configure(com.fasterxml.jackson.databind.SerializationFeature.
            WRITE_DATES_AS_TIMESTAMPS, false);
  }

  @Override
  public String toGson(Object object) {
    try {
      return toGson(object, false);
    }
    catch (JsonProcessingException e) {
      logger.warn(e.getMessage(), e);
      return StringUtils.EMPTY;
    }
  }

  @Override
  public <T> T fromGson(String json, Class<T> clazzx) {
    try {
      return defaultMapper().readValue(json, clazzx);
    }
    catch (IOException e) {
      logger.warn(e.getMessage(), e);
      return null;
    }
  }

  @Override
  public <T> T fromGson(InputStream json, Class<T> clazzx) {
    try {
      return defaultMapper().readValue(json, clazzx);
    }
    catch (IOException e) {
      logger.warn(e.getMessage(), e);
      return null;
    }
  }

  @Override
  public HttpEntity toStringEntity(Object object) throws UnsupportedEncodingException, JsonProcessingException {
    if (Objects.isNull(object)) {
      throw new BadRequestException(BAD_REQUEST, "Object is empty", null, null);
    }
    return new StringEntity(toGson(object, false));
  }

  private List<Header> defaultHeaders() {
    return Lists.newArrayList(createHeader(CONTENT_TYPE, "application/json"), createHeader(ACCEPT, "application/json"));
  }

  @Override
  public HttpGet buildGet(String url, Header[] headers) {
    HttpGet get = new HttpGet(url);
    if (headers != null && headers.length > 0) {
      get.setHeaders(headers);
    }
    return get;
  }

  @Override
  public HttpPost buildPost(String url, Object object, Header[] headers) {
    try {
      if (StringUtils.isEmpty(url)) {
        throw new BadRequestException(BAD_REQUEST, "Bad or empty url ", null, null);
      }
      HttpPost post = new HttpPost(url);
      post.setHeaders(headers);
      post.setEntity(toStringEntity(object));
      return post;
    }
    catch (UnsupportedEncodingException | JsonProcessingException e) {
      logger.warn(e.getMessage(), e);
      throw new BadRequestException(BAD_REQUEST, "Failed to create postobject", e.getMessage(), e);
    }
  }

  @Override
  public void validateResponse(HttpResponse response, int expectedReturnCode) {
    if (Objects.isNull(response)) {
      throw new BadRequestException(BAD_REQUEST, "Input is empty", "HttpResonse object is null", null);
    }
    try {
      if (response.getStatusLine().getStatusCode() != expectedReturnCode) {
        logger.warn("Input rc = {} , expected {}", response.getStatusLine().getStatusCode(), expectedReturnCode);
        InputStream is = response.getEntity().getContent();
        throw new BadRequestException(response.getStatusLine().getStatusCode(), "Failed to validate response", Objects.nonNull(is) ? IOUtils.toString(is, UTF_8) : null, null);
      }
    }
    catch (IOException ex) {
      logger.warn(ex.getMessage(), ex);
      throw new BadRequestException(BAD_REQUEST, "Failed to validate response", "Failed to read content of response", null);
    }
  }

  private Header createHeader(String key, String value) {
    return new BasicHeader(key, value);
  }
}

class ListParameterizedType implements ParameterizedType {

  private Type type;

  public ListParameterizedType(Type type) {
    this.type = type;
  }

  @Override
  public Type[] getActualTypeArguments() {
    return new Type[] {type};
  }

  @Override
  public Type getRawType() {
    return ArrayList.class;
  }

  @Override
  public Type getOwnerType() {
    return null;
  }
}