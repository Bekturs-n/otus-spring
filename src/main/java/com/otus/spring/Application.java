package com.otus.spring;

import com.otus.spring.service.TestService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
    TestService testService = applicationContext.getBean(TestService.class);
    System.out.println(testService.testing());
  }

}
