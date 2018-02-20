package com.insurance.agreement;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.insurance.agreement.utils.HttpUtil;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:/testApplication.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public abstract class Base {

  @LocalServerPort
  private int applicationPort;
  @Autowired
  protected TestRestTemplate restTemplate;
  @Autowired
  protected HttpUtil util;
  @Autowired
  protected MockMvc mvc;
  protected static final String TEST_VALUE = "TEST";
  protected static final String UUID_REGEX = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}";

  protected String buildContextPath() {

    return String.format("%s:%d", "http://localhost", applicationPort);
  }

  @SpringBootApplication
  @ComponentScan(basePackages = {"com.insurance.agreement"})
  @EntityScan(basePackages = {"com.insurance.agreement"})
  static class TestConfiguration {

  }
}
