package com.insurance.agreement.soap.service.impl;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.time.ZoneId;
import java.util.GregorianCalendar;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.agreement.soap.api.Agreement;
import com.insurance.agreement.soap.api.AgreementType;
import com.insurance.agreement.soap.api.Customer;
import com.insurance.agreement.soap.dao.CustomerDao;
import com.insurance.agreement.soap.model.AgreementModel;
import com.insurance.agreement.soap.model.CustomerModel;
import com.insurance.agreement.soap.service.ObjectMapper;

@Service
public class ObjectMapperImpl implements ObjectMapper {

  private final CustomerDao customerDao;

  private Logger logger = LoggerFactory.getLogger(ObjectMapperImpl.class);

  @Autowired
  public ObjectMapperImpl(CustomerDao customerDao) {
    this.customerDao = customerDao;
  }

  @Override
  public Customer mapCustomerModelToXml(CustomerModel model) {
    Customer customer = new Customer();
    customer.setId(model.getApplicationId());
    customer.setAddresse(model.getAddresse());
    customer.setCity(model.getCity());
    customer.setCountry(model.getCountry());
    customer.setName(model.getName());
    try {
      customer.setUpdated(DatatypeFactory.newInstance().newXMLGregorianCalendar(GregorianCalendar.from(model.getUpdated().atZone(ZoneId.systemDefault()))));
    }
    catch (DatatypeConfigurationException e) {
      logger.error(e.getMessage(), e);
    }
    return customer;
  }

  @Override
  public CustomerModel mapCustomerXmlToModel(Customer xml) {
    CustomerModel model = new CustomerModel();
    model.setAddresse(xml.getAddresse());
    model.setApplicationId(xml.getId());
    model.setCity(xml.getCity());
    model.setZipCode(xml.getZipcode());
    model.setCountry(xml.getCountry());
    model.setName(xml.getName());
    if (xml.getUpdated() != null) {
      model.setUpdated(xml.getUpdated().toGregorianCalendar().toZonedDateTime().toLocalDateTime());
    }
    return model;
  }

  @Override
  public AgreementModel mapAgreementXmlToModel(Agreement xml) {
    AgreementModel model = new AgreementModel();
    model.setAgreementName(xml.getAgreementName());
    model.setAgreementType(xml.getAgreementType().value());
    model.setApplicationId(xml.getId());
    if (Objects.isNull(xml.getCustomer().getId())) {
      throw new RuntimeException("CUstomer neeed to be created first");
    }
    model.setCustomer(customerDao.findCustomer(xml.getCustomer().getId()));
    model.setSentToCustomer(xml.isIsSentToCustomer());

    return model;
  }

  @Override
  public Agreement mapAgreementModelToXml(AgreementModel model) {

    Agreement xml = new Agreement();
    xml.setCustomer(mapCustomerModelToXml(model.getCustomer()));
    try {
      xml.setUpdated(DatatypeFactory.newInstance().newXMLGregorianCalendar(GregorianCalendar.from(model.getUpdated().atZone(ZoneId.systemDefault()))));
    }
    catch (DatatypeConfigurationException e) {
      logger.error(e.getMessage(), e);
    }
    xml.setAgreementName(model.getAgreementName());
    xml.setAgreementType(AgreementType.valueOf(model.getAgreementType()));
    xml.setId(model.getApplicationId());

    return xml;
  }
}
