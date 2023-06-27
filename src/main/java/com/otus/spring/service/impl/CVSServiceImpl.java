package com.otus.spring.service.impl;

import com.otus.spring.dao.TaskDao;
import com.otus.spring.model.Task;
import com.otus.spring.reader.CsvReader;
import com.otus.spring.service.CVSService;
import com.otus.spring.writer.CsvWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CVSServiceImpl implements CVSService {

  private final TaskDao taskDao;
  private final CsvWriter csvWriter;
  private final CsvReader csvReader;

  @Override
  public List<Task> getAll() {
    return taskDao.getAlltasks();
  }

  @Override
  public void saveResult(String userName, Integer correctAnswer, String result) {
    try {
      csvWriter.writeData(userName, correctAnswer, result);
      System.out.println("Sava success!");
    } catch (IOException e) {
      log.error("Error during save data", e);
    }
  }

  @Override
  public List<String> getLastResultByUserName(String userName) {
    return csvReader.getLastResultByUserName(userName);
  }
}
