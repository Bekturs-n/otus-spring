package com.otus.spring.service.impl;

import com.otus.spring.dao.TaskDao;
import com.otus.spring.model.Task;
import com.otus.spring.service.CVSService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CVSServiceImpl implements CVSService {

  private final TaskDao taskDao;

  public List<Task> getAll() {
    return taskDao.getAlltasks();
  }

}
