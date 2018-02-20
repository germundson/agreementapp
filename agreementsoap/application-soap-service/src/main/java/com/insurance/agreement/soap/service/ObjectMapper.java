package com.insurance.agreement.soap.service;

import com.insurance.agreement.soap.api.Agreement;
import com.insurance.agreement.soap.api.Customer;
import com.insurance.agreement.soap.model.AgreementModel;
import com.insurance.agreement.soap.model.CustomerModel;

public interface ObjectMapper {

  Customer mapCustomerModelToXml(CustomerModel model);

  CustomerModel mapCustomerXmlToModel(Customer model);

  AgreementModel mapAgreementXmlToModel(Agreement xml);

  Agreement mapAgreementModelToXml(AgreementModel model);
}
