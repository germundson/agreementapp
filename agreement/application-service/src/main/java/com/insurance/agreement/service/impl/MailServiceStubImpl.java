package com.insurance.agreement.service.impl;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.insurance.agreement.api.agreement.AgreementApi;
import com.insurance.agreement.service.MailService;

@Service
@Profile("test")
public class MailServiceStubImpl implements MailService {

  @Override
  public boolean sendAgreement(AgreementApi agreementApi) {
    return true;
  }
}
