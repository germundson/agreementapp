package com.insurance.agreement.utils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_FORBIDDEN;
import static org.apache.http.HttpStatus.SC_GATEWAY_TIMEOUT;
import static org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR;
import static org.apache.http.HttpStatus.SC_MOVED_PERMANENTLY;
import static org.apache.http.HttpStatus.SC_MOVED_TEMPORARILY;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_NOT_IMPLEMENTED;
import static org.apache.http.HttpStatus.SC_NOT_MODIFIED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_PAYMENT_REQUIRED;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;

public class HttpConstants {

  public static final Charset UTF_8 = StandardCharsets.UTF_8;
  public static final Charset ISO_88591 = StandardCharsets.ISO_8859_1;

  private HttpConstants() {
    //SONAR
  }

  //2xx
  public static final int OK = SC_OK;
  public static final int CREATED = SC_CREATED;
  public static final int ACCEPTED = SC_ACCEPTED;
  //3xx
  public static final int MOVED = SC_MOVED_PERMANENTLY;
  public static final int FOUND = SC_MOVED_TEMPORARILY;
  public static final int NOT_MODIFIED = SC_NOT_MODIFIED;
  //4xx
  public static final int BAD_REQUEST = SC_BAD_REQUEST;
  public static final int UNAUTHORIZED = SC_UNAUTHORIZED;
  public static final int PAYMENT_REQUIRED = SC_PAYMENT_REQUIRED;
  public static final int FORBIDDEN = SC_FORBIDDEN;
  public static final int NOTFOUND = SC_NOT_FOUND;

  //5xx
  public static final int INTERNAL_ERROR = SC_INTERNAL_SERVER_ERROR;
  public static final int NOT_IMPLEMENTED = SC_NOT_IMPLEMENTED;
  public static final int GATEWAY_TIMEOUT = SC_GATEWAY_TIMEOUT;

  //HEADERS
  public static final String AUTHORIZATION = org.apache.http.HttpHeaders.AUTHORIZATION;
  public static final String BEARER = "Bearer";

  public static final String GET = "GET";
  public static final String POST = "POST";
  public static final String PUT = "PUT";
  public static final String DELETE = "DELETE";
  public static final String OPTION = "OPTION";

  public static final String JSON_EXTENSION = ".json";
}
