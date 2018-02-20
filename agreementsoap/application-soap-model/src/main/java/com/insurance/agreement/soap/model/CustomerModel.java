package com.insurance.agreement.soap.model;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDateTime;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.insurance.agreement.soap.model.converter.LocalDateTimeConverter;

@Entity
@Table(name = "Customer")
public class CustomerModel {

  private Long id;
  private LocalDateTime created;
  private LocalDateTime updated;
  private String applicationId;
  private String name;
  private String addresse;
  private String zipCode;
  private String city;
  private String country;

  public CustomerModel() {
    update();
    created = LocalDateTime.now();
  }

  @GenericGenerator(
      name = "customerSequenceGenerator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
          @Parameter(name = "sequence_name", value = "customer_seq"),
          @Parameter(name = "initial_value", value = "1"),
          @Parameter(name = "increment_size", value = "1")
      }
  )
  @GeneratedValue(generator = "customerSequenceGenerator")
  @Id
  public Long getId() {
    return id;
  }

  @Column
  @Convert(converter = LocalDateTimeConverter.class)
  public LocalDateTime getCreated() {
    return created;
  }

  @Column
  @Convert(converter = LocalDateTimeConverter.class)
  public LocalDateTime getUpdated() {
    return updated;
  }

  @Column
  public String getApplicationId() {
    return applicationId;
  }

  @Column
  public String getName() {
    return name;
  }

  @Column
  public String getAddresse() {
    return addresse;
  }

  @Column
  public String getZipCode() {
    return zipCode;
  }

  @Column
  public String getCity() {
    return city;
  }

  @Column
  public String getCountry() {
    return country;
  }

  @Transient
  private void update() {
    this.updated = LocalDateTime.now();
  }

  private void setId(Long id) {
    this.id = id;
  }

  public void setCreated(LocalDateTime created) {
    update();
    this.created = created;
  }

  public void setUpdated(LocalDateTime updated) {
    update();
    this.updated = updated;
  }

  public void setApplicationId(String applicationId) {
    update();
    this.applicationId = applicationId;
  }

  public void setName(String name) {
    update();
    this.name = name;
  }

  public void setAddresse(String addresse) {
    update();
    this.addresse = addresse;
  }

  public void setZipCode(String zipCode) {
    update();
    this.zipCode = zipCode;
  }

  public void setCity(String city) {
    update();
    this.city = city;
  }

  public void setCountry(String country) {
    update();
    this.country = country;
  }
}
