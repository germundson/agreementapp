package com.insurance.agreement.soap.dao.impl;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.insurance.agreement.soap.dao.AgreementDao;
import com.insurance.agreement.soap.dao.Dao;
import com.insurance.agreement.soap.model.AgreementModel;

import static javax.transaction.Transactional.TxType.REQUIRED;

@Repository
@Transactional(REQUIRED)
public class AgreementDaoImpl extends Dao<AgreementModel> implements AgreementDao {

  @Override
  public AgreementModel findAgreement(String uuid) {
    try {
      return entityManager.createQuery("from Agreement A where applicationId=:applicationId", AgreementModel.class).setParameter("applicationId", uuid).getSingleResult();
    }
    catch (PersistenceException ex) {
      logger.error(ex.getMessage(), ex);
      return null;
    }
  }

  @Override
  public AgreementModel findAgreement(Long id) {
    return entityManager.find(AgreementModel.class, id);
  }

  @Override
  public AgreementModel saveAgreement(AgreementModel agreement) {
    return save(agreement);
  }

  @Override
  public boolean exists(String uuid) {
    return findAgreement(uuid) != null;
  }
}
