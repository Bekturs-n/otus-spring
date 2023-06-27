package com.otus.spring.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

//лучше под него(EnableConfigurationProperties) сделать отдельный класс для конфигурации
@Configuration
@EnableConfigurationProperties(AppProperties.class)
public class ApplicationConfig {

  @Bean
  public ResourceBundleMessageSource messageSource() {
    ResourceBundleMessageSource source = new ResourceBundleMessageSource();
    source.setBasenames("i18n/messages");
    return source;
  }
}
