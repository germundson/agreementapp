package com.insurance.agreement.soap.dao;

import com.insurance.agreement.soap.model.AgreementModel;

public interface AgreementDao {

  AgreementModel findAgreement(String uuid);

  AgreementModel findAgreement(Long id);

  AgreementModel saveAgreement(AgreementModel agreement);

  boolean exists(String uuid);
}
