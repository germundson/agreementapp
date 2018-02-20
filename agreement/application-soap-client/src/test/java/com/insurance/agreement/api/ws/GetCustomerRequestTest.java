package com.insurance.agreement.api.ws;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.net.URL;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class GetCustomerRequestTest {

  @Test
  public void testUsignApi() throws IOException {
    GetCustomerRequest getCustomerRequest = new GetCustomerRequest();
    Customer customer = new Customer();
    customer.setAddresse("?");
    customer.setCity("?");
    customer.setCountry("?");
    customer.setName("?");
    customer.setZipcode("?");
    getCustomerRequest.setCustomer(customer);

    URL url = new URL("http://localhost:8070/ws/agreements.wsdl");

    QName qName = new QName("http://insurance.app.com/ws", "AgreementsPortService");

    AgreementsPortService port = new AgreementsPortService(url, qName);
    GetCustomerResponse customerResponse = port.getAgreementsPortSoap11().getCustomer(getCustomerRequest);
    assertNotNull("Customerresponse is empty", customerResponse);
    assertNotNull("Response does not contain customer", customerResponse.getCustomer());
    assertNotNull("Customer has not been persisted", customerResponse.getCustomer().getId());

    assertTrue(true);
  }
}
