package com.insurance.agreement.api.authentication;

import javax.annotation.Generated;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.insurance.agreement.api.Item;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Department extends Item {

  @SerializedName("active")
  public Boolean Active;
  @SerializedName("contactPerson")
  public String ContactPerson;
  @SerializedName("email")
  public String Email;
  @SerializedName("extReference")
  public String ExtReference;
  @SerializedName("id")
  public Long Id;
  @SerializedName("links")
  public Links Links;
  @SerializedName("name")
  public String Name;
  @SerializedName("orgNumber")
  public String OrgNumber;
  @SerializedName("organizationId")
  public Long OrganizationId;
  @SerializedName("phone")
  public String Phone;
  @SerializedName("postalAddress")
  public String PostalAddress;
  @SerializedName("postalCode")
  public String PostalCode;
  @SerializedName("users")
  public List<User> Users;
}
