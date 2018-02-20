package com.insurance.agreement.exceptions;

public class BadRequestException extends RuntimeException {

  private int httpCode = 400;
  private Throwable throwable;
  private String debug;

  public BadRequestException(String msg) {
    super(msg);
  }

  public BadRequestException(String s, Throwable throwable) {
    super(s, throwable);
  }

  public BadRequestException(Throwable throwable) {
    super(throwable);
  }

  public BadRequestException(int httpCode, String message, String debug, Throwable throwable) {
    super(message, throwable);
    this.httpCode = httpCode;
    this.debug = debug;
    this.throwable = throwable;
  }

  public int getHttpCode() {
    return httpCode;
  }

  public Throwable getThrowable() {
    return throwable;
  }

  public String getDebugMessage() {
    return debug;
  }
}
