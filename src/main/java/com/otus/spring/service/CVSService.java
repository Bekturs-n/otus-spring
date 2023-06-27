package com.otus.spring.service;

import com.otus.spring.model.Task;

import java.util.List;

public interface CVSService {

  List<Task> getAll();

  void saveResult(String userName, Integer correctAnswer, String result);

  List<String> getLastResultByUserName(String userName);

}
