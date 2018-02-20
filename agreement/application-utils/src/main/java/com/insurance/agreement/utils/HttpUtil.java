package com.insurance.agreement.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface HttpUtil {

  HttpResponse execute(HttpRequestBase command) throws IOException;

  void printResult(HttpResponse response) throws IOException;

  <T> List<T> getListResult(HttpResponse response, Class<T> clazz) throws IOException;

  <T> T getResult(HttpResponse response, Class<T> clazz) throws IOException;

  <T> T getResult(String response, Class<T> clazz) throws IOException;

  void printResult(HttpResponse response, Properties properties) throws IOException;

  HttpGet buildGet(String url);

  HttpPost buildPost(String url, String body) throws UnsupportedEncodingException;

  HttpPost buildPost(String url, Object body) throws UnsupportedEncodingException, JsonProcessingException;

  String toGson(Object object) throws JsonProcessingException;

  String toGson(Object object, boolean formated) throws JsonProcessingException;

  <T> T fromGson(String json, Class<T> clazzx);

  <T> T fromGson(InputStream json, Class<T> clazzx);

  HttpEntity toStringEntity(Object object) throws UnsupportedEncodingException, JsonProcessingException;

  HttpGet buildGet(String url, Header[] headers);

  HttpPost buildPost(String url, Object object, Header[] headers);

  void validateResponse(HttpResponse response, int expectedReturnCode);
}
