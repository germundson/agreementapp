package com.insurance.agreement.app.controller;

import org.junit.Test;

import com.insurance.agreement.api.ping.PingApi;
import com.insurance.agreement.app.Base;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PingControllerTest extends Base {

  @Test
  public void pingShouldBeNoneNull() throws Exception {
    assertNotNull(this.restTemplate.getForObject(buildContextPath() + "/systemping", String.class));
  }

  @Test
  public void pingShouldContainField() throws Exception {
    assertTrue(this.restTemplate.getForObject(buildContextPath() + "/systemping", String.class).contains("status_time"));
  }

  @Test
  public void pingShouldTransformToPingApi() throws Exception {
    PingApi api = util.getResult(this.restTemplate.getForObject(buildContextPath() + "/systemping", String.class), PingApi.class);
    assertNotNull(api);
    assertNotNull(api.statusTime);
  }
}
