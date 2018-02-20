package com.insurance.agreement.api.authentication;

import javax.annotation.Generated;

import com.google.gson.annotations.SerializedName;
import com.insurance.agreement.api.Item;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Parameter extends Item {

  @SerializedName("name")
  public String Name;
  @SerializedName("rules")
  public String Rules;
  @SerializedName("sysId")
  public String SysId;
  @SerializedName("value")
  public String Value;
}
