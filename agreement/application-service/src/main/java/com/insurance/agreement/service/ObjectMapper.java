package com.insurance.agreement.service;

import com.insurance.agreement.api.agreement.AgreementApi;
import com.insurance.agreement.api.agreement.CustomerApi;
import com.insurance.agreement.api.ws.Agreement;
import com.insurance.agreement.api.ws.Customer;

public interface ObjectMapper {

  Customer MapCustomerJsonToXml(CustomerApi api);

  CustomerApi MapCutomerXmlToJson(Customer api);

  Agreement MapAgreementJsonToXml(AgreementApi api);

  AgreementApi MapAgreementXmlToJson(Agreement api);
}
