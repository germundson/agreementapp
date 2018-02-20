package com.insurance.agreement.api.authentication;

import javax.annotation.Generated;

import com.google.gson.annotations.SerializedName;
import com.insurance.agreement.api.Item;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class PostalAddress extends Item {

  @SerializedName("address")
  public String Address;
  @SerializedName("links")
  public Links Links;
  @SerializedName("postalCode")
  public String PostalCode;
  @SerializedName("postalOffice")
  public String PostalOffice;
}
