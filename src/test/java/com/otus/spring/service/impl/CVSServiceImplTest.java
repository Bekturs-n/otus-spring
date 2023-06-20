package com.otus.spring.service.impl;

import com.otus.spring.reader.CsvReader;
import com.otus.spring.service.abstracts.CVSService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class CVSServiceImplTest {

  @Mock
  private CsvReader csvReader;

  private CVSService cvsService;

  @BeforeEach
  void setIp(){
    cvsService = new CVSServiceImpl(csvReader);
  }

  @Test
  public void getAll() {
  }
}