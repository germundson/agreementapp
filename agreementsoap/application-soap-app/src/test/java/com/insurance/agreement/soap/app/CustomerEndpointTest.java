package com.insurance.agreement.soap.app;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.insurance.agreement.soap.Base;
import com.insurance.agreement.soap.api.Customer;
import com.insurance.agreement.soap.api.GetCustomerRequest;
import com.insurance.agreement.soap.api.GetCustomerResponse;

import static org.junit.Assert.assertNotNull;

public class CustomerEndpointTest extends Base {

  private static final String TEST_VALUE = "TEST";
  @Autowired
  CustomerEndpoint customerEndpoint;

  @Test
  public void testCustomerEndpoint() {

    GetCustomerRequest customerRequest = new GetCustomerRequest();
    Customer customer = new Customer();
    customer.setName(TEST_VALUE);
    customer.setAddresse(TEST_VALUE);
    customer.setZipcode(TEST_VALUE);
    customer.setCity(TEST_VALUE);

    customerRequest.setCustomer(customer);

    GetCustomerResponse response = customerEndpoint.getCustomer(customerRequest);
    assertNotNull(response);
  }
}