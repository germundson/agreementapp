package com.insurance.agreement.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import io.swagger.annotations.Api;
import springfox.documentation.annotations.ApiIgnore;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@ApiIgnore
@Api(value = "Api", tags = "Api", consumes = "application/json", produces = "application/json", hidden = true)
@RestController
@RequestMapping("/")
public class ApiController {

  @RequestMapping(method = GET)
  public RedirectView apiController(RedirectAttributes attributes) {
    return new RedirectView("/swagger-ui.html");
  }
}
