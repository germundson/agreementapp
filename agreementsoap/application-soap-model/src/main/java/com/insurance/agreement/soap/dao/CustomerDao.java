package com.insurance.agreement.soap.dao;

import com.insurance.agreement.soap.model.CustomerModel;

public interface CustomerDao {

  CustomerModel findCustomer(String uuid);

  CustomerModel findCustomer(Long id);

  CustomerModel saveCustomer(CustomerModel customer);

  boolean exists(String uuid);
}
