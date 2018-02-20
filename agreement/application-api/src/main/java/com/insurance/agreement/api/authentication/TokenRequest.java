package com.insurance.agreement.api.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenRequest {

  @JsonProperty
  public String grant_type;
  @JsonProperty
  public String client_id;
  @JsonProperty
  public String client_secret;
  @JsonProperty
  public String username;
  @JsonProperty
  public String password;
  @JsonProperty
  public String refresh_token;
  @JsonProperty
  public String scope;
}
