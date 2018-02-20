package com.insurance.agreement.dao.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.insurance.agreement.dao.Dao;
import com.insurance.agreement.dao.TransactionDao;
import com.insurance.agreement.model.Log;

import static javax.transaction.Transactional.TxType.REQUIRED;

@Transactional(REQUIRED)
@Repository
@Component
public class TransactionDaoImpl extends Dao implements TransactionDao {

  @Override
  public Log saveLog(Log log) {
    entityManager.persist(log);
    return log;
  }
}
