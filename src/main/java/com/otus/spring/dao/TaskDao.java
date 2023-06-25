package com.otus.spring.dao;

import com.otus.spring.model.Task;

import java.util.List;

public interface TaskDao {

  List<Task> getAlltasks();

  Task getById(Integer id);

}
