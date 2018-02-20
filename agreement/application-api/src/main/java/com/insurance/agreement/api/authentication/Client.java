package com.insurance.agreement.api.authentication;

import javax.annotation.Generated;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.insurance.agreement.api.Item;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Client extends Item {

  @SerializedName("authorizationCode")
  public String AuthorizationCode;
  @SerializedName("clientId")
  public String ClientId;
  @SerializedName("clientSecret")
  public String ClientSecret;
  @SerializedName("confidential")
  public Boolean Confidential;
  @SerializedName("description")
  public String Description;
  @SerializedName("firstDate")
  public String FirstDate;
  @SerializedName("internal")
  public Boolean Internal;
  @SerializedName("isActive")
  public Boolean IsActive;
  @SerializedName("lastDate")
  public String LastDate;
  @SerializedName("scopes")
  public List<String> Scopes;
  @SerializedName("trusted")
  public Boolean Trusted;
}
