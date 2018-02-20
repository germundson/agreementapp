package com.insurance.agreement.soap.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.agreement.soap.api.Agreement;
import com.insurance.agreement.soap.dao.AgreementDao;
import com.insurance.agreement.soap.service.AgreementService;
import com.insurance.agreement.soap.service.ObjectMapper;

@Service
public class AgreementServiceImpl implements AgreementService {

  private final AgreementDao agreementDao;
  private final ObjectMapper objectMapper;

  @Autowired
  public AgreementServiceImpl(AgreementDao agreementDao, ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
    this.agreementDao = agreementDao;
  }

  @Override
  public Agreement persistAgreement(Agreement agreement) {

    agreement.setId(UUID.randomUUID().toString());

    return objectMapper.mapAgreementModelToXml(agreementDao.saveAgreement(objectMapper.mapAgreementXmlToModel(agreement)));
  }
}
