package com.insurance.agreement.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.insurance.agreement.Base;
import com.insurance.agreement.api.ping.PingApi;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

public class PingServiceTest extends Base {

  @Autowired
  PingService service;

  @Test
  public void testPingService() {
    PingApi api = service.ping();

    assertNotNull(api);
    assertTrue(api.online);
  }
}
