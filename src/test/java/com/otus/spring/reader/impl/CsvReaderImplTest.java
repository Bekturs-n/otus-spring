package com.otus.spring.reader.impl;

import com.otus.spring.reader.CsvReader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CsvReaderImplTest {

  @Mock
  private CsvReader csvReader;

  @Test
  void readAllFile() {
    List<List<String>> expected = List.of(Arrays.asList("1", "2", "3"));
    Mockito.when(csvReader.readAllFile()).thenReturn(List.of(Arrays.asList("1", "2", "3")));
    assertEquals(expected, csvReader.readAllFile());
  }
}