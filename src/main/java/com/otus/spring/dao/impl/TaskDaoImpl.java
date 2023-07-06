package com.otus.spring.dao.impl;

import com.otus.spring.dao.TaskDao;
import com.otus.spring.model.Task;
import com.otus.spring.reader.CsvReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TaskDaoImpl implements TaskDao {

  private final CsvReader csvReader;

  @Override
  public List<Task> getAlltasks() {
    List<Task> tasks = new ArrayList<>();
    List<List<String>> allRawData = csvReader.getAllFileData();

    allRawData.forEach(cell -> tasks.add(Task.builder()
        .id(Integer.parseInt(cell.get(0)))
        .question(cell.get(1))
        .option(Arrays.asList(cell.get(2), cell.get(3), cell.get(4), cell.get(5)))
        .answer(cell.get(6).trim())
        .build()));

    return tasks;
  }

}
