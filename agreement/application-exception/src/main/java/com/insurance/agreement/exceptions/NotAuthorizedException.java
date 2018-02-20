package com.insurance.agreement.exceptions;

public class NotAuthorizedException extends RuntimeException {

  public NotAuthorizedException() {
    super();
  }

  public NotAuthorizedException(String msg) {
    super(msg);
  }

  public NotAuthorizedException(String s, Throwable throwable) {
    super(s, throwable);
  }

  public NotAuthorizedException(Throwable throwable) {
    super(throwable);
  }
}
