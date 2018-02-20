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
public class AgreementApi extends Item {

  @ApiModelProperty
  @JsonProperty
  public String agreementNumber;
  @ApiModelProperty
  @JsonProperty
  public String agreementName;
  @ApiModelProperty
  @JsonProperty
  public AgreementType agrementType;
  @ApiModelProperty
  @JsonProperty
  public CustomerApi customer;
  @ApiModelProperty
  @JsonProperty
  public boolean isSentToCustomer;
  @JsonProperty("updated")
  @ApiModelProperty(name = "updated")
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  public LocalDateTime updated;
}
