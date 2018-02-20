package com.insurance.agreement.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.agreement.api.ping.PingApi;
import com.insurance.agreement.service.PingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import static com.insurance.agreement.utils.HttpConstants.INTERNAL_ERROR;
import static com.insurance.agreement.utils.HttpConstants.OK;
import static com.insurance.agreement.utils.HttpConstants.UNAUTHORIZED;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Api(value = "Ping API", tags = "Ping", consumes = "application/json", produces = "application/json")
@RestController
@RequestMapping("/systemping")
public class PingController {

  @Autowired
  private PingService pingService;

  @ApiOperation(notes = "Resource that gives system helth",
      value = "System helth",
      httpMethod = "GET",
      nickname = "helth",
      response = PingApi.class,
      consumes = "application/json",
      produces = "application/json"
  )
  @ApiResponses({
      @ApiResponse(code = OK, message = "Ordeline list returned"),
      @ApiResponse(code = INTERNAL_ERROR, message = "Server error"),
      @ApiResponse(code = UNAUTHORIZED, message = "Authentication failed")
  })
  @RequestMapping(method = GET)
  public PingApi ping() {
    return pingService.ping();
  }
}
