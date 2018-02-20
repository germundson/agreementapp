package com.insurance.agreement.api.ws;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.net.URL;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class GetAgreementRequestTest {

  private static final String TEST_VALUE = "TEST_VALUE";

  @Test
  public void testUsignApi() throws IOException {
    URL url = new URL("http://localhost:8070/ws/agreements.wsdl");
    QName qName = new QName("http://insurance.app.com/ws", "AgreementsPortService");
    AgreementsPortService port = new AgreementsPortService(url, qName);

    GetCustomerRequest getCustomerRequest = new GetCustomerRequest();
    GetAgreementRequest agreementRequest = new GetAgreementRequest();
    Agreement agreement = new Agreement();

    Customer customer = new Customer();
    customer.setAddresse(TEST_VALUE);
    customer.setCity(TEST_VALUE);
    customer.setCountry(TEST_VALUE);
    customer.setName(TEST_VALUE);
    customer.setZipcode(TEST_VALUE);
    getCustomerRequest.setCustomer(customer);

    GetCustomerResponse customerResponse = port.getAgreementsPortSoap11().getCustomer(getCustomerRequest);
    assertNotNull("Customerresponse is empty", customerResponse);
    assertNotNull("Response does not contain customer", customerResponse.getCustomer());
    assertNotNull("Customer has not been persisted", customerResponse.getCustomer().getId());

    agreement.setCustomer(customerResponse.getCustomer());
    agreement.setAgreementName(TEST_VALUE);
    agreement.setAgreementType(AgreementType.TYPE_1);
    agreement.setIsSentToCustomer(false);

    agreementRequest.setAgreement(agreement);

    GetAgreementResponse agreementResponse = port.getAgreementsPortSoap11().getAgreement(agreementRequest);
    assertNotNull("Agreement is empty", agreementResponse);
    assertNotNull("Does not contain an agreement", agreementResponse.getAgreement());
    assertNotNull("Agreement has not been persisted", agreementResponse.getAgreement().getId());
  }
}
