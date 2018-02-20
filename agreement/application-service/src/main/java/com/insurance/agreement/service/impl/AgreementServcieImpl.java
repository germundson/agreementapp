package com.insurance.agreement.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.insurance.agreement.api.agreement.AgreementApi;
import com.insurance.agreement.api.ws.Agreement;
import com.insurance.agreement.api.ws.AgreementType;
import com.insurance.agreement.api.ws.GetAgreementRequest;
import com.insurance.agreement.api.ws.GetAgreementResponse;
import com.insurance.agreement.exceptions.BadRequestException;
import com.insurance.agreement.service.AgreementService;
import com.insurance.agreement.service.CustomerService;
import com.insurance.agreement.service.MailService;
import com.insurance.agreement.service.ObjectMapper;
import com.insurance.agreement.service.SoapService;

@Component
@Service
public class AgreementServcieImpl implements AgreementService {

  private final Logger logger = LoggerFactory.getLogger(AgreementServcieImpl.class);
  private final CustomerService customerService;
  private final MailService mailService;
  private final SoapService soapService;
  private final ObjectMapper mapper;

  @Autowired
  public AgreementServcieImpl(CustomerService customerService, MailService mailService, SoapService soapService, ObjectMapper mapper) {
    this.customerService = customerService;
    this.mailService = mailService;
    this.soapService = soapService;
    this.mapper = mapper;
  }

  @Override
  public AgreementApi createAgreement(AgreementApi agreementApi) {
    try {
      AgreementApi api = agreementApi;
      api.customer = customerService.createCustomer(api.customer);
      api = sendToSoap(api);
      api.isSentToCustomer = mailService.sendAgreement(api);
      return api;
    }
    catch (BadRequestException e) {
      logger.error(e.getMessage(), e);
      throw new BadRequestException("Failed to create agreement");
    }

    //update fagsystem with sentstatus

  }

  private AgreementApi sendToSoap(AgreementApi api) {

    GetAgreementRequest request = new GetAgreementRequest();
    Agreement agreement = new Agreement();
    agreement.setAgreementName(api.agreementName);
    agreement.setAgreementType(AgreementType.fromValue(api.agrementType.name()));
    agreement.setIsSentToCustomer(false);
    agreement.setCustomer(mapper.MapCustomerJsonToXml(api.customer));
    request.setAgreement(agreement);

    GetAgreementResponse response = soapService.sendAgreement(request);

    if (response == null || response.getAgreement() == null) {
      throw new BadRequestException("Failed to send agreement");
    }
    return mapper.MapAgreementXmlToJson(response.getAgreement());
  }
}
