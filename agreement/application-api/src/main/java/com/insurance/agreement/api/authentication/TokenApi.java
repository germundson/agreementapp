package com.insurance.agreement.api.authentication;

import javax.annotation.Generated;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.insurance.agreement.api.Item;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class TokenApi extends Item {

  @SerializedName("client")
  public Client client;
  @SerializedName("expires_in")
  public Long ExpiresIn;
  @SerializedName("grant_type")
  public String GrantType;
  @SerializedName("scopes")
  public List<String> Scopes;
  @SerializedName("user")
  public User User;
}
