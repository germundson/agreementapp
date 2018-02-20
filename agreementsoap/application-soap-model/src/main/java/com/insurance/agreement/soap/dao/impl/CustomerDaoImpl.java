package com.insurance.agreement.soap.dao.impl;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.insurance.agreement.soap.dao.CustomerDao;
import com.insurance.agreement.soap.dao.Dao;
import com.insurance.agreement.soap.model.CustomerModel;

import static javax.transaction.Transactional.TxType.REQUIRED;

@Repository
@Transactional(REQUIRED)
public class CustomerDaoImpl extends Dao<CustomerModel> implements CustomerDao {

  private Logger logger = LoggerFactory.getLogger(CustomerDaoImpl.class);

  @Override
  public CustomerModel findCustomer(String uuid) {
    try {
      return entityManager.
          createQuery("from CustomerModel where applicationId=:applicationId", CustomerModel.class).
          setParameter("applicationId", uuid).
          getSingleResult();
    }
    catch (PersistenceException ex) {
      logger.error(ex.getMessage(), ex);
      return null;
    }
  }

  @Override
  public CustomerModel findCustomer(Long id) {
    return entityManager.find(CustomerModel.class, id);
  }

  @Override
  public CustomerModel saveCustomer(CustomerModel customer) {
    return save(customer);
  }

  @Override
  public boolean exists(String uuid) {
    return findCustomer(uuid) != null;
  }
}
