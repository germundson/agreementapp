package com.insurance.agreement.api.ping;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.insurance.agreement.api.Item;
import com.insurance.agreement.api.deserilizer.LocalDateTimeDeserializer;
import com.insurance.agreement.api.serilizer.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@ApiModel("Ping")
public class PingApi extends Item {

  @ApiModelProperty
  public Boolean online;

  @JsonProperty("status_time")
  @ApiModelProperty(name = "status_time")
  public Long statusTime;

  @ApiModelProperty
  public String system;

  @ApiModelProperty
  public String version;

  @ApiModelProperty
  public String env;

  @ApiModelProperty
  public boolean db;

  @JsonProperty("release_time")
  @ApiModelProperty(name = "release_time")
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  public LocalDateTime releaseTime;

  @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd")
  @JsonProperty("started_time")
  @ApiModelProperty(name = "started_time")
  public LocalDateTime startedTime;
  @ApiModelProperty(name = "buildtime")
  public String buildTime;
}