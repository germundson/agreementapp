package com.insurance.agreement.api.agreement;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.insurance.agreement.api.Item;
import com.insurance.agreement.api.deserilizer.LocalDateTimeDeserializer;
import com.insurance.agreement.api.serilizer.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class CustomerApi extends Item {

  @ApiModelProperty
  @JsonProperty
  public String id;
  @ApiModelProperty
  @JsonProperty
  public String name;
  @ApiModelProperty
  @JsonProperty
  public String addresse;
  @ApiModelProperty
  @JsonProperty
  public String zipCode;
  @ApiModelProperty
  @JsonProperty
  public String city;
  @ApiModelProperty
  @JsonProperty
  public String country;
  @JsonProperty("updated")
  @ApiModelProperty(name = "updated")
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  public LocalDateTime updated;
}
