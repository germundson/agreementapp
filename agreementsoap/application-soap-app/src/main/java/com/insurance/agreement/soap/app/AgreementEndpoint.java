package com.insurance.agreement.soap.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.insurance.agreement.soap.api.GetAgreementRequest;
import com.insurance.agreement.soap.api.GetAgreementResponse;
import com.insurance.agreement.soap.service.AgreementService;

@Endpoint
public class AgreementEndpoint {

  private static final String NAMESPACE_URI = "http://insurance.app.com/ws";

  @Autowired
  AgreementService service;

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAgreementRequest")
  @ResponsePayload
  public GetAgreementResponse getAgreement(@RequestPayload GetAgreementRequest request) {
    GetAgreementResponse response = new GetAgreementResponse();
    response.setAgreement(service.persistAgreement(request.getAgreement()));
    return response;
  }
}