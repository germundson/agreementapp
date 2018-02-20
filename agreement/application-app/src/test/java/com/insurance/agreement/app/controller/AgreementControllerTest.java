package com.insurance.agreement.app.controller;

import java.io.IOException;
import java.util.Collections;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import com.insurance.agreement.api.ping.PingApi;
import com.insurance.agreement.app.Base;

import static com.insurance.agreement.utils.HttpConstants.UTF_8;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;

public class AgreementControllerTest extends Base {

  String body;

  @Before
  public void setup() throws IOException {
    body = IOUtils.toString(AgreementControllerTest.class.getResourceAsStream("/com/insurance/agreement/app/controller/agreementOK.json"), UTF_8);
  }

  @Test
  public void aggreementShoulnotBenull() {

    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Collections.singletonList(APPLICATION_JSON));
    headers.setContentType(APPLICATION_JSON);

    HttpEntity<String> entity = new HttpEntity<>(body, headers);

    assertNotNull(this.restTemplate.postForObject(buildContextPath() + "/agreement", entity, String.class));
  }

  @Test
  public void aggreementShouldContainField() {
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Collections.singletonList(APPLICATION_JSON));
    headers.setContentType(APPLICATION_JSON);

    HttpEntity<String> entity = new HttpEntity<>(body, headers);
    assertTrue(this.restTemplate.postForObject(buildContextPath() + "/agreement", entity, String.class).contains("agreementNumber"));
  }

  @Test
  public void pingShouldTransformToPingApi() throws Exception {
    PingApi api = util.getResult(this.restTemplate.getForObject(buildContextPath() + "/systemping", String.class), PingApi.class);
    assertNotNull(api);
    assertNotNull(api.statusTime);
  }
}