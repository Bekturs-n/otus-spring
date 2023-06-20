package com.otus.spring.reader;

import com.otus.spring.model.Task;

import java.util.List;

public interface CsvReader {

  Task getById(Integer id);

  List<Task> getAllTasks();
}
