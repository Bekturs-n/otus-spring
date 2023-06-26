package com.otus.spring.dao.impl;

import com.otus.spring.dao.TaskDao;
import com.otus.spring.model.Task;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
class TaskDaoImplTest {

  @Mock
  private TaskDao taskDao;

  @Test
  public void testGetAlltasks() {
    List<Task> expected = Arrays.asList(Task.builder().id(1).build());
    Mockito.when(taskDao.getAlltasks()).thenReturn(Arrays.asList(Task.builder().id(1).build()));
    assertEquals(expected, taskDao.getAlltasks());
  }
}