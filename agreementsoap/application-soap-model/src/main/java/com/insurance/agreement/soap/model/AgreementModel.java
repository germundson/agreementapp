package com.insurance.agreement.soap.model;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDateTime;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.insurance.agreement.soap.model.converter.LocalDateTimeConverter;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "Agreement")
public class AgreementModel {

  private Long id;
  private String applicationId;
  private LocalDateTime created;
  private LocalDateTime updated;
  private String agreementName;
  private String agreementType;
  private CustomerModel customer;
  private boolean sentToCustomer;

  public AgreementModel() {
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
  public String getAgreementName() {
    return agreementName;
  }

  @Column
  public String getAgreementType() {
    return agreementType;
  }

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "customer_id", nullable = false)
  public CustomerModel getCustomer() {
    return customer;
  }

  @Column
  public boolean isSentToCustomer() {
    return sentToCustomer;
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

  public void setAgreementName(String agreementName) {
    update();
    this.agreementName = agreementName;
  }

  public void setAgreementType(String agreementType) {
    update();
    this.agreementType = agreementType;
  }

  public void setCustomer(CustomerModel customer) {
    update();
    this.customer = customer;
  }

  public void setSentToCustomer(boolean sentToCustomer) {
    update();
    this.sentToCustomer = sentToCustomer;
  }
}
