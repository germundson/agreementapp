package com.insurance.agreement.app;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.insurance.agreement"})
@EntityScan(basePackages = {"com.insurance.agreement"})
public class ApplicationConfiguration {

}
