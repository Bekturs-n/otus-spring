package com.otus.spring.reader.impl;

import com.opencsv.CSVReader;
import com.otus.spring.config.AppProperties;
import com.otus.spring.reader.CsvReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CsvReaderImpl implements CsvReader {

  private final AppProperties appProperties;
  private final MessageSource messageSource;

  @Override
  public List<List<String>> readAllFile() {
    String absolutePath = new File("").getAbsolutePath();
    String filePath = messageSource.getMessage("faile.path", null, appProperties.getLocale());
    List<List<String>> data = new ArrayList<>();

    try (CSVReader csvReader = new CSVReader(new FileReader(absolutePath + filePath))) {
      String[] nextRecord;
      // skip the first cell
      csvReader.readNext();
      while ((nextRecord = csvReader.readNext()) != null) {
        String[] cell = nextRecord[0].split(";");
        data.add(Arrays.asList(cell));
      }
    } catch (IOException e) {
      log.error("Ошибка при работе с файлом", e);
    }

    return data;
  }

  @Override
  public List<String> readCellByRowNumber(Integer rowNumber) {
    // пока не требуется
    return null;
  }

}
