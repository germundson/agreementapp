package com.insurance.agreement.api.authentication;

import javax.annotation.Generated;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.insurance.agreement.api.Item;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Organization extends Item {

  @SerializedName("active")
  public Boolean Active;
  @SerializedName("address")
  public String Address;
  @SerializedName("authorizations")
  public List<Authorization> Authorizations;
  @SerializedName("axaptaCustomerNumber")
  public String AxaptaCustomerNumber;
  @SerializedName("code")
  public String Code;
  @SerializedName("dateDeleted")
  public String DateDeleted;
  @SerializedName("email")
  public String Email;
  @SerializedName("href")
  public String Href;
  @SerializedName("id")
  public Long Id;
  @SerializedName("isDemoUser")
  public Boolean IsDemoUser;
  @SerializedName("isDistributor")
  public Boolean IsDistributor;
  @SerializedName("isIntegrator")
  public Boolean IsIntegrator;
  @SerializedName("links")
  public Links Links;
  @SerializedName("name")
  public String Name;
  @SerializedName("organizationType")
  public String OrganizationType;
  @SerializedName("orgnr")
  public String Orgnr;
  @SerializedName("parameters")
  public List<Parameter> Parameters;
  @SerializedName("phone")
  public String Phone;
  @SerializedName("postalAddress")
  public String PostalAddress;
  @SerializedName("postalCode")
  public String PostalCode;
  @SerializedName("rel")
  public String Rel;
  @SerializedName("supplierSettings")
  public SupplierSettings SupplierSettings;
  @SerializedName("type")
  public String Type;
}
