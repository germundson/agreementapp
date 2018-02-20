package com.insurance.agreement.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.insurance.agreement.Base;
import com.insurance.agreement.api.agreement.AgreementApi;
import com.insurance.agreement.api.agreement.AgreementType;
import com.insurance.agreement.api.agreement.CustomerApi;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

public class AgreementServiceTest extends Base {

  @Autowired
  private AgreementService service;

  @Test
  public void testCreateAgreement() {
    AgreementApi agreementApi = new AgreementApi();
    CustomerApi customerApi = new CustomerApi();
    customerApi.country = TEST_VALUE;
    customerApi.city = TEST_VALUE;
    customerApi.zipCode = TEST_VALUE;
    customerApi.addresse = TEST_VALUE;
    customerApi.name = TEST_VALUE;
    agreementApi.agrementType = AgreementType.TYPE_1;
    agreementApi.agreementName = TEST_VALUE;
    agreementApi.customer = customerApi;

    agreementApi = service.createAgreement(agreementApi);
    assertNotNull(agreementApi);
    assertNotNull(agreementApi.agreementNumber);
    assertTrue(agreementApi.agreementNumber.matches(UUID_REGEX));
  }
}
