package com.insurance.agreement.service.impl;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.time.ZoneId;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.insurance.agreement.api.agreement.AgreementApi;
import com.insurance.agreement.api.agreement.CustomerApi;
import com.insurance.agreement.api.ws.Agreement;
import com.insurance.agreement.api.ws.AgreementType;
import com.insurance.agreement.api.ws.Customer;
import com.insurance.agreement.service.ObjectMapper;

@Service
public class ObjectMapperImpl implements ObjectMapper {

  Logger logger = LoggerFactory.getLogger(ObjectMapperImpl.class);

  @Override
  public Customer MapCustomerJsonToXml(CustomerApi customerApi) {
    Customer customer = new Customer();
    customer.setId(customerApi.id);
    customer.setAddresse(customerApi.addresse);
    customer.setCity(customerApi.city);
    customer.setCountry(customerApi.country);
    customer.setName(customerApi.country);
    customer.setZipcode(customerApi.zipCode);
    if (customerApi.updated != null) {
      try {
        customer.setUpdated(DatatypeFactory.newInstance().newXMLGregorianCalendar(GregorianCalendar.from(customerApi.updated.atZone(ZoneId.systemDefault()))));
      }
      catch (DatatypeConfigurationException e) {
        logger.error(e.getMessage(), e);
      }
    }
    return customer;
  }

  @Override
  public CustomerApi MapCutomerXmlToJson(Customer xml) {
    CustomerApi api = new CustomerApi();
    api.id = xml.getId();
    api.name = xml.getName();
    api.addresse = xml.getAddresse();
    api.zipCode = xml.getZipcode();
    api.city = xml.getCity();
    api.country = xml.getCountry();
    if (xml.getUpdated() != null) {
      api.updated = xml.getUpdated().toGregorianCalendar().toZonedDateTime().toLocalDateTime();
    }
    return api;
  }

  @Override
  public Agreement MapAgreementJsonToXml(AgreementApi api) {
    Agreement agreement = new Agreement();
    agreement.setCustomer(MapCustomerJsonToXml(api.customer));
    agreement.setIsSentToCustomer(api.isSentToCustomer);
    agreement.setAgreementType(AgreementType.fromValue(api.agrementType.name()));
    agreement.setAgreementName(api.agreementName);
    if (api.updated != null) {

      try {
        agreement.setUpdated(DatatypeFactory.newInstance().newXMLGregorianCalendar(GregorianCalendar.from(api.updated.atZone(ZoneId.systemDefault()))));
      }
      catch (DatatypeConfigurationException e) {
        logger.error(e.getMessage(), e);
      }
    }
    return agreement;
  }

  @Override
  public AgreementApi MapAgreementXmlToJson(Agreement xml) {
    AgreementApi api = new AgreementApi();

    api.isSentToCustomer = xml.isIsSentToCustomer();
    api.agreementName = xml.getAgreementName();
    api.agreementNumber = xml.getId();
    api.agrementType = com.insurance.agreement.api.agreement.AgreementType.valueOf(xml.getAgreementType().name());
    api.customer = MapCutomerXmlToJson(xml.getCustomer());

    if (xml.getUpdated() != null) {
      api.updated = xml.getUpdated().toGregorianCalendar().toZonedDateTime().toLocalDateTime();
    }
    return api;
  }
}
