package com.insurance.agreement.soap.app;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.insurance.agreement.soap.app", "com.insurance.agreement.soap.service","com.insurance.agreement.soap.dao"})
@EntityScan(basePackages = "com.insurance.agreement.soap.model")
public class ApplicationConfig {

}
