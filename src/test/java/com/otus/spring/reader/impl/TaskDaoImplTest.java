package com.otus.spring.reader.impl;

import com.otus.spring.dao.impl.TaskDaoImpl;
import com.otus.spring.model.Task;
import com.otus.spring.reader.CsvReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskDaoImplTest {

  private TaskDaoImpl taskDao;

  @Mock
  private CsvReader csvReader;

  @BeforeEach
  void beforeEach() {
    taskDao = new TaskDaoImpl(csvReader);
  }

  @Test
  void readAllFile() {
    when(csvReader.readAllFile()).thenReturn(
        Collections.singletonList(Arrays.asList("1", "How much will be 5+5", "1", "2", "10", "4", "10")));

    List<Task> task = Collections.singletonList(
        Task.builder().id(1).question("How much will be 5+5").option(Arrays.asList("1", "2", "10", "4")).answer("10")
            .build());

    assertEquals(task, taskDao.getAlltasks());
  }
}