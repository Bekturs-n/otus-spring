package com.otus.spring.service.impl;

import com.otus.spring.model.Task;
import com.otus.spring.service.abstracts.CVSService;
import com.otus.spring.service.abstracts.TestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class TestServiceImpl implements TestService {

  private String studentName;
  private final Scanner scanner = new Scanner(System.in);

  @Value("${zachet.value}")
  private Integer passedPoint;

  private CVSService cvsService;

  public TestServiceImpl(CVSService cvsService) {
    this.cvsService = cvsService;
  }

  @Override
  public void fillStudentsData() {
    System.out.println("Ваша имя и фамилия?");
    studentName = scanner.nextLine();
  }

  @Override
  public String testing() {
    int correctAnswer = 0;
    var total = "Вы не сдали";
    List<Task> tasks = cvsService.getAll();

    fillStudentsData();
    for (Task task : tasks) {
      System.out.println(task.getQuestion());
      System.out.println("Варианты:");
      task.getOption().forEach(System.out::print);
      System.out.println();

      if (task.getAnswer().equalsIgnoreCase(scanner.nextLine())) {
        correctAnswer++;
      }
    }
    if (correctAnswer >= passedPoint) {
      total = "Вы сдали";
    }

    return studentName + total;
  }

}
