package com.insurance.agreement.service;

import com.insurance.agreement.api.agreement.AgreementApi;

public interface MailService {

  boolean sendAgreement(AgreementApi agreementApi);
}
