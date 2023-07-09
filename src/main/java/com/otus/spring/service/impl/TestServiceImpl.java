package com.otus.spring.service.impl;

import com.otus.spring.config.AppProperties;
import com.otus.spring.model.Task;
import com.otus.spring.service.CVSService;
import com.otus.spring.service.IOService;
import com.otus.spring.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

  private String studentName;
  private final CVSService cvsService;
  private final MessageSource messageSource;
  private final AppProperties appProperties;
  private final IOService ioService;

  @Override
  public void fillStudentsData() {
    var ask = messageSource.getMessage("ask.name", null, appProperties.getLocale());
    ioService.setNextString(ask);
    studentName = ioService.getNextString();
    ioService.setNextString(
        messageSource.getMessage("user.hello", new String[] { studentName }, appProperties.getLocale()));
  }

  @Override
  public String testing() {
    var total = "user.not.passed";
    int correctAnswer = 0;
    List<Task> tasks = cvsService.getAll();

    fillStudentsData();
    for (Task task : tasks) {
      var variant = messageSource.getMessage("variant", null, appProperties.getLocale());
      ioService.setNextString(task.getQuestion());
      ioService.setNextString(variant);
      task.getOption().forEach(ioService::setNextString);
      ioService.makeIndent();

      if (task.getAnswer().equalsIgnoreCase(ioService.getNextString())) {
        correctAnswer++;
      }
    }
    if (correctAnswer >= 3) {
      total = "user.passed";
    }

    return messageSource.getMessage(total, new String[] { studentName }, appProperties.getLocale());
  }

}
