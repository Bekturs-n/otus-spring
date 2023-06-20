package com.otus.spring.reader;


import com.otus.spring.model.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class CsvReaderImplTest {

  @Autowired
  private CsvReader csvReader;

  private List<Task> list = Collections.singletonList(Task.builder()
      .id(1)
      .questions("How much will be 5+5")
      .option(Arrays.asList(" 1", " 2", " 10", " 4"))
      .answer("10")
      .build());

  @Test
  public void getAllTasks() {
    List<Task> testList = csvReader.getAllTasks();
    Assertions.assertEquals(list, testList);
  }

}