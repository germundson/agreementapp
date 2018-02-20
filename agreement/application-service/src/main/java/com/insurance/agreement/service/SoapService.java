package com.insurance.agreement.service;

import com.insurance.agreement.api.ws.GetAgreementRequest;
import com.insurance.agreement.api.ws.GetAgreementResponse;
import com.insurance.agreement.api.ws.GetCustomerRequest;
import com.insurance.agreement.api.ws.GetCustomerResponse;

public interface SoapService {

  GetCustomerResponse sendCustomer(GetCustomerRequest request);

  GetAgreementResponse sendAgreement(GetAgreementRequest request);
}
