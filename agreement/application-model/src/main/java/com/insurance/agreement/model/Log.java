package com.insurance.agreement.model;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.insurance.agreement.converter.LocalDateTimeConverter;

@Entity
@Table
public class Log {

  private String method;
  private Long id;
  private LocalDateTime timestamp;
  private String token;
  private String dataObj;
  private String direction;

  private Log() {
    timestamp = LocalDateTime.now();
  }

  public Log(String body) {
    this();
    this.dataObj = body;
  }

  public Log(String body, String token) {
    this();
    dataObj = body;
    this.token = token;
  }

  public Log(String method, String body, String token) {
    this();
    this.dataObj = body;
    this.method = method;
    this.token = token;
  }

  @Id
  @GenericGenerator(
      name = "logSequenceGenerator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
          @Parameter(name = "sequence_name", value = "log_log_seq"),
          @Parameter(name = "initial_value", value = "1"),
          @Parameter(name = "increment_size", value = "1")
      }
  )
  @GeneratedValue(generator = "logSequenceGenerator")
  public Long getId() {
    return id;
  }

  @Column
  @Convert(converter = LocalDateTimeConverter.class)
  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  @Column
  public String getDataObj() {
    return dataObj;
  }

  @Column
  public String getToken() {
    return token;
  }

  @Column
  public String getMethod() {
    return method;
  }

  @Column
  public String getDirection() {
    return direction;
  }

  private void setId(Long id) {
    this.id = id;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public void setDataObj(String dataObj) {
    this.dataObj = dataObj;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }
}
