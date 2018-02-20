package com.insurance.agreement.soap.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.insurance.agreement.soap.api.GetCustomerRequest;
import com.insurance.agreement.soap.api.GetCustomerResponse;
import com.insurance.agreement.soap.service.CustomerService;

@Endpoint
public class CustomerEndpoint {

  private static final String NAMESPACE_URI = "http://insurance.app.com/ws";

  @Autowired
  CustomerService service;

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCustomerRequest")
  @ResponsePayload
  public GetCustomerResponse getCustomer(@RequestPayload GetCustomerRequest request) {
    GetCustomerResponse response = new GetCustomerResponse();
    response.setCustomer(service.persistCustomer(request.getCustomer()));

    return response;
  }
}