package com.insurance.agreement.api.authentication;

import javax.annotation.Generated;

import com.google.gson.annotations.SerializedName;
import com.insurance.agreement.api.Item;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class SupplierMessage extends Item {

  @SerializedName("from")
  public String From;
  @SerializedName("message")
  public String Message;
  @SerializedName("to")
  public String To;
}
