package com.insurance.agreement.api.error;

import javax.validation.ConstraintViolation;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.insurance.agreement.api.Item;

public class ApiError extends Item {

  @JsonProperty("status")
  private HttpStatus status;
  @JsonProperty("timestamp")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
  private LocalDateTime timestamp;
  @JsonProperty("message")
  private String message;
  @JsonProperty("debugmessage")
  private String debugMessage;
  @JsonProperty("supErrors")
  private List<ApiSubError> subErrors;

  public ApiError() {
    timestamp = LocalDateTime.now();
  }

  public ApiError(HttpStatus conflict, String database_error, Throwable cause) {
    this();
  }

  public ApiError(HttpStatus status) {
    this();
    this.status = status;
  }

  public ApiError(HttpStatus status, String message, RuntimeException ex) {
    this();
    this.status = status;
    this.message = message;
    this.debugMessage = ex.getMessage();
  }

  public ApiError(HttpStatus status, RuntimeException e) {

    this.status = status;
    this.debugMessage = e.getMessage();
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void addValidationErrors(List<FieldError> fieldErrors) {

    subErrors = new ArrayList<>();
    fieldErrors.forEach(k -> subErrors.add(new ApiValidationError(k.getField(), k.getDefaultMessage())));
  }

  public void addValidationError(List<ObjectError> globalErrors) {

    globalErrors.forEach(k -> subErrors.add(new ApiValidationError(k.getObjectName(), k.toString())));
  }

  public void setDebugMessage(String debugMessage) {
    this.debugMessage = debugMessage;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  }

  public void addValidationErrors(Set<ConstraintViolation<?>> constraintViolations) {

  }
}