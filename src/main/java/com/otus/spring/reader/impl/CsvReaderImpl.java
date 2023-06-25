package com.otus.spring.reader.impl;

import com.opencsv.CSVReader;
import com.otus.spring.model.Task;
import com.otus.spring.reader.CsvReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class CsvReaderImpl implements CsvReader {

  @Value("${file.path}")
  private String filePath;

  @Override
  public List<List<String>> getAllFileData() {
    String absolutePath = new File("").getAbsolutePath();
    List<List<String>> allFileData = new ArrayList<>();

    try (CSVReader csvReader = new CSVReader(new FileReader(absolutePath + filePath))) {
      String[] nextRecord;
      // skip the first cell
      csvReader.readNext();
      while ((nextRecord = csvReader.readNext()) != null) {
        String[] cell = nextRecord[0].split(";");
        allFileData.add(Arrays.asList(cell));
      }
    } catch (IOException e) {
      log.error("Ошибка при работе с файлом", e);
    }

    return allFileData;
  }

  @Override
  public Task getById(Integer id) {
    // пока не надо
    return null;
  }

}
