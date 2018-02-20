package com.insurance.agreement.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:/testApplication.properties")
@SpringBootTest
public class ApplicationTest {

  @Test
  public void contextLoads() throws Exception {
  }
}