package com.insurance.agreement.api.authentication;

import javax.annotation.Generated;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.insurance.agreement.api.Item;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Token extends Item {

  @JsonProperty
  public String access_token;
  @JsonProperty
  public Long expires_in;
  @JsonProperty
  public String refresh_token;
  @JsonProperty
  public List<String> scope;
  @JsonProperty
  public String token_type;
}
