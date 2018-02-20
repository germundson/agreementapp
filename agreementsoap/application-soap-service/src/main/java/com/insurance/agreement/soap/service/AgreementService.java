package com.insurance.agreement.soap.service;

import com.insurance.agreement.soap.api.Agreement;

public interface AgreementService {

  Agreement persistAgreement(Agreement agreement);
}
