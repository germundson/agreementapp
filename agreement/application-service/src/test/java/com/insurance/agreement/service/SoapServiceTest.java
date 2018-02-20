package com.insurance.agreement.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.insurance.agreement.Base;
import com.insurance.agreement.api.ws.Customer;
import com.insurance.agreement.api.ws.GetAgreementRequest;
import com.insurance.agreement.api.ws.GetCustomerRequest;

public class SoapServiceTest extends Base {

  private static final String TEST_VALUE = "TEST_VALUE";
  @Autowired
  private SoapService service;

  @Test
  public void testSendCustomer() {
    GetCustomerRequest getCustomerRequest = new GetCustomerRequest();
    GetAgreementRequest agreementRequest = new GetAgreementRequest();

    Customer customer = new Customer();
    customer.setAddresse(TEST_VALUE);
    customer.setCity(TEST_VALUE);
    customer.setCountry(TEST_VALUE);
    customer.setName(TEST_VALUE);
    customer.setZipcode(TEST_VALUE);
    getCustomerRequest.setCustomer(customer);

    service.sendCustomer(getCustomerRequest);
  }
}