package com.otus.spring.service.impl;

import com.otus.spring.reader.CsvReader;
import com.otus.spring.service.CVSService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class CVSServiceImplTest {

  @Mock
  private CsvReader csvReader;

  private CVSService cvsService;

  @Test
  public void getAll() {
  }
}