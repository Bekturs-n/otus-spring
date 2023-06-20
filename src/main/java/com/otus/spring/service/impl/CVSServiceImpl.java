package com.otus.spring.service.impl;

import com.otus.spring.model.Task;
import com.otus.spring.reader.CsvReader;
import com.otus.spring.service.abstracts.CVSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CVSServiceImpl implements CVSService {

  private CsvReader csvReader;

  @Autowired
  public CVSServiceImpl(CsvReader csvReader) {
    this.csvReader = csvReader;
  }

  public List<Task> getAll() {
    return csvReader.getAllTasks();
  }

}
