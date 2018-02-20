package com.insurance.agreement.soap.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.agreement.soap.api.Customer;
import com.insurance.agreement.soap.dao.CustomerDao;
import com.insurance.agreement.soap.service.CustomerService;
import com.insurance.agreement.soap.service.ObjectMapper;

@Service
public class CustomerServiceImpl implements CustomerService {

  private final CustomerDao customerDao;
  private final ObjectMapper objectMapper;

  @Autowired
  public CustomerServiceImpl(CustomerDao customerDao, ObjectMapper objectMapper) {
    this.customerDao = customerDao;
    this.objectMapper = objectMapper;
  }

  @Override
  public Customer persistCustomer(Customer customer) {
    customer.setId(UUID.randomUUID().toString());
    return objectMapper.mapCustomerModelToXml(customerDao.saveCustomer(objectMapper.mapCustomerXmlToModel(customer)));
  }
}
