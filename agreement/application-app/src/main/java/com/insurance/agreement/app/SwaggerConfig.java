package com.insurance.agreement.app;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.insurance.agreement.app.controller"))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(builApiInfo());
  }

  private ApiInfo builApiInfo() {
    return new ApiInfo(
        "Insurence Api",
        "Some custom description of API.",
        "1.0-SNAPSHOT",
        "Terms of service",
        new Contact("Some Org As", "www.somebody.com", "support@somebody.com"),
        "License of API", "API license URL", Collections.emptyList());
  }
}