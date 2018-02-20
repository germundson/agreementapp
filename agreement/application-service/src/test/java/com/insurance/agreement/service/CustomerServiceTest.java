package com.insurance.agreement.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.insurance.agreement.Base;
import com.insurance.agreement.api.agreement.CustomerApi;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

public class CustomerServiceTest extends Base {

  @Autowired
  private CustomerService customerService;

  @Test
  public void testCreateCustomer() {
    CustomerApi api = new CustomerApi();
    api.country = TEST_VALUE;
    api.city = TEST_VALUE;
    api.zipCode = TEST_VALUE;
    api.addresse = TEST_VALUE;
    api.name = TEST_VALUE;

    api = customerService.createCustomer(api);
    assertNotNull(api);
    assertNotNull(api.id);
    assertTrue(api.id.matches(UUID_REGEX));
  }
}
