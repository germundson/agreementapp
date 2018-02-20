package com.insurance.agreement.api.authentication;

import javax.annotation.Generated;

import com.google.gson.annotations.SerializedName;
import com.insurance.agreement.api.Item;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Authorization extends Item {

  @SerializedName("code")
  public String Code;
  @SerializedName("description")
  public String Description;
}
