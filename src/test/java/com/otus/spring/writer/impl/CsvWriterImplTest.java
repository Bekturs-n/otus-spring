package com.otus.spring.writer.impl;

import com.otus.spring.writer.CsvWriter;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
class CsvWriterImplTest {

  @Mock
  private CsvWriter csvWriter;

  @Test
  void writeDataToFile() throws IOException {
    boolean expected = false;
    Mockito.when(csvWriter.writeData(anyString(), anyInt(), anyString())).thenReturn(false);
    assertEquals(expected, csvWriter.writeData(anyString(), anyInt(), anyString()));
  }

}