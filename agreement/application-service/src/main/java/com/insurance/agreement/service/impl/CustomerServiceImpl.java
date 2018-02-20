package com.insurance.agreement.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.insurance.agreement.api.agreement.CustomerApi;
import com.insurance.agreement.api.ws.Customer;
import com.insurance.agreement.api.ws.GetCustomerRequest;
import com.insurance.agreement.api.ws.GetCustomerResponse;
import com.insurance.agreement.exceptions.BadRequestException;
import com.insurance.agreement.service.CustomerService;
import com.insurance.agreement.service.ObjectMapper;
import com.insurance.agreement.service.SoapService;

@Component
@Service
public class CustomerServiceImpl implements CustomerService {

  private final SoapService soapService;
  private final ObjectMapper objectMapper;
  private final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

  @Autowired
  public CustomerServiceImpl(SoapService soapService, ObjectMapper objectMapper) {
    this.soapService = soapService;
    this.objectMapper = objectMapper;
  }

  @Override
  public CustomerApi createCustomer(CustomerApi customerApi) {
    CustomerApi api = customerApi;
    api = send(api);
    return api;
  }

  private CustomerApi send(CustomerApi customerApi) {

    GetCustomerRequest getCustomerRequest = new GetCustomerRequest();
    Customer customer = new Customer();
    customer.setAddresse(customerApi.addresse);
    customer.setCity(customerApi.city);
    customer.setCountry(customerApi.country);
    customer.setName(customerApi.country);
    customer.setZipcode(customerApi.zipCode);
    getCustomerRequest.setCustomer(customer);

    GetCustomerResponse response = soapService.sendCustomer(getCustomerRequest);

    if (response == null) {
      throw new BadRequestException("FAILED TO CONNECT TO PROVIDER");
    }

    customerApi = objectMapper.MapCutomerXmlToJson(response.getCustomer());
    return customerApi;
  }
}
