package com.kuliao.horcrux.app.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@RequestMapping("/configpro")
public class ConfigProController {

  private static final String template = "Hello, %s!";

  @Value("${providerkey:providerkeydefault}")
  private String name;

  @GetMapping("/providerkey")
  public String getProviderkKey() {
    return String.format(template, name);
  }

}
