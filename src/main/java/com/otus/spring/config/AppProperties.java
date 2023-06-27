package com.otus.spring.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;

@Data
@ConfigurationProperties(prefix = "application")
public class AppProperties {

  private String filePath;
  private String zachetValue;
  private Locale locale;

}
