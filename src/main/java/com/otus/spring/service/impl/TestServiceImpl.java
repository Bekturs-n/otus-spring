package com.otus.spring.service.impl;

import com.otus.spring.config.AppProperties;
import com.otus.spring.model.Task;
import com.otus.spring.service.abstracts.CVSService;
import com.otus.spring.service.abstracts.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class TestServiceImpl implements TestService {

  private String studentName;
  private final Scanner scanner = new Scanner(System.in);
  private final CVSService cvsService;
  private final MessageSource messageSource;
  private final AppProperties appProperties;

  @Autowired
  public TestServiceImpl(CVSService cvsService, MessageSource messageSource, AppProperties appProperties) {
    this.cvsService = cvsService;
    this.messageSource = messageSource;
    this.appProperties = appProperties;
  }

  @Override
  public void fillStudentsData() {
    var ask = messageSource.getMessage("ask.name", null, appProperties.getLocale());
    System.out.println(ask);
    studentName = scanner.nextLine();
    System.out.println(messageSource.getMessage("user.hello", new String[] { studentName }, appProperties.getLocale()));
  }

  @Override
  public String testing() {
    var total = "user.not.passed";
    int correctAnswer = 0;
    List<Task> tasks = cvsService.getAll();

    fillStudentsData();
    for (Task task : tasks) {
      var variant = messageSource.getMessage("variant", null, appProperties.getLocale());
      System.out.println(task.getQuestion());
      System.out.println(variant);
      task.getOption().forEach(System.out::print);
      System.out.println();

      if (task.getAnswer().equalsIgnoreCase(scanner.nextLine())) {
        correctAnswer++;
      }
    }
    if (correctAnswer >= 3) {
      total = "user.passed";
    }

    return messageSource.getMessage(total, new String[] { studentName }, appProperties.getLocale());
  }

}
