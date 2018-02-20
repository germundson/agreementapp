package com.insurance.agreement.service.impl;

import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceException;
import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.insurance.agreement.api.ws.AgreementsPortService;
import com.insurance.agreement.api.ws.GetAgreementRequest;
import com.insurance.agreement.api.ws.GetAgreementResponse;
import com.insurance.agreement.api.ws.GetCustomerRequest;
import com.insurance.agreement.api.ws.GetCustomerResponse;
import com.insurance.agreement.exceptions.BadRequestException;
import com.insurance.agreement.service.SoapService;

@Service
public class SoapServiceImpl implements SoapService {

  private final Logger logger = LoggerFactory.getLogger(SoapServiceImpl.class);

  @Override
  public GetCustomerResponse sendCustomer(GetCustomerRequest request) {
    try {
      return buildService().getAgreementsPortSoap11().getCustomer(request);
    }
    catch (MalformedURLException e) {
      logger.error(e.getMessage(), e);
      return null;
    }
    catch (WebServiceException exception) {
      logger.error(exception.getMessage(), exception);
      throw new BadRequestException("Failed to execute soap", exception);
    }
  }

  @Override
  public GetAgreementResponse sendAgreement(GetAgreementRequest request) {
    try {
      return buildService().getAgreementsPortSoap11().getAgreement(request);
    }
    catch (MalformedURLException e) {
      logger.error(e.getMessage(), e);
      return null;
    }
    catch (WebServiceException exception) {
      logger.error(exception.getMessage(), exception);
      throw new BadRequestException("Failed to execute soap", exception);
    }
  }

  private AgreementsPortService buildService() throws MalformedURLException {
    URL url = new URL("http://localhost:8070/ws/agreements.wsdl");

    QName qName = new QName("http://insurance.app.com/ws", "AgreementsPortService");

    return new AgreementsPortService(url, qName);
  }
}
