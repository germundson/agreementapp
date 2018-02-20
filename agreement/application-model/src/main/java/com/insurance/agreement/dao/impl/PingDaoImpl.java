package com.insurance.agreement.dao.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.insurance.agreement.dao.Dao;
import com.insurance.agreement.dao.PingDao;

import static javax.transaction.Transactional.TxType.REQUIRED;

@Transactional(REQUIRED)
@Repository
@Component
public class PingDaoImpl extends Dao implements PingDao {

  @Override
  public void ping() {
    entityManager.createNativeQuery("SELECT 'Hello world'").getResultList();
  }
}
