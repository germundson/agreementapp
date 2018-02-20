package com.insurance.agreement.api.authentication;

import javax.annotation.Generated;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.insurance.agreement.api.Item;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class User extends Item {

  @SerializedName("active")
  public Boolean Active;
  @SerializedName("authorizations")
  public List<Authorization> Authorizations;
  @SerializedName("department")
  public Department Department;
  @SerializedName("departmentId")
  public Long DepartmentId;
  @SerializedName("departmentName")
  public String DepartmentName;
  @SerializedName("email")
  public String Email;
  @SerializedName("id")
  public Long Id;
  @SerializedName("isDemoUser")
  public Boolean IsDemoUser;
  @SerializedName("links")
  public Links Links;
  @SerializedName("logonIdent")
  public String LogonIdent;
  @SerializedName("name")
  public String Name;
  @SerializedName("organization")
  public Organization Organization;
  @SerializedName("parameters")
  public List<Parameter> Parameters;
  @SerializedName("phone")
  public String Phone;
  @SerializedName("postalAddress")
  public PostalAddress PostalAddress;
  @SerializedName("token")
  public Token Token;
}
