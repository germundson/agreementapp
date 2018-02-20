package com.insurance.agreement.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.agreement.api.agreement.AgreementApi;
import com.insurance.agreement.exceptions.BadRequestException;
import com.insurance.agreement.service.AgreementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import static com.insurance.agreement.utils.HttpConstants.BAD_REQUEST;
import static com.insurance.agreement.utils.HttpConstants.INTERNAL_ERROR;
import static com.insurance.agreement.utils.HttpConstants.OK;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Api(value = "Agreement API", tags = "Agreement", consumes = "application/json", produces = "application/json")
@RestController
@RequestMapping("/agreement")
public class AgreementController {

  private final AgreementService agreementService;
  private Logger logger = LoggerFactory.getLogger(AgreementController.class);

  @Autowired
  public AgreementController(AgreementService agreementService) {
    this.agreementService = agreementService;
  }

  @ApiOperation(notes = "Resource that creates agreement",
      value = "Create agreement",
      httpMethod = "POST",
      nickname = "agreement",
      response = AgreementApi.class,
      consumes = "application/json",
      produces = "application/json"

  )
  @ApiResponses({
      @ApiResponse(code = OK, message = "Agreement created"),
      @ApiResponse(code = BAD_REQUEST, message = "Wrong input "),
      @ApiResponse(code = INTERNAL_ERROR, message = "Server error")
  })

  @RequestMapping(method = POST)
  public AgreementApi createAgreement(@RequestBody AgreementApi agreementApi) {

    logger.info("Start");
    try {
      if (agreementApi == null) {
        throw new BadRequestException("Empty body");
      }

      return agreementService.createAgreement(agreementApi);
    }
    finally {
      logger.info("end");
    }
  }
}
