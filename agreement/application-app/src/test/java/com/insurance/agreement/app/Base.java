package com.insurance.agreement.app;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
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

  protected String buildContextPath() {

    return String.format("%s:%d", "http://localhost", applicationPort);
  }
}
