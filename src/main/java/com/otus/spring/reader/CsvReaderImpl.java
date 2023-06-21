package com.otus.spring.reader;

import com.opencsv.CSVReader;
import com.otus.spring.config.AppProperties;
import com.otus.spring.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CsvReaderImpl implements CsvReader {

  Logger logger = LoggerFactory.getLogger(CsvReaderImpl.class);

  private final AppProperties appProperties;
  private final MessageSource messageSource;

  public CsvReaderImpl(AppProperties appProperties, MessageSource messageSource) {
    this.messageSource = messageSource;
    this.appProperties = appProperties;
  }

  public List<Task> getAllTasks() {
    String filePath = messageSource.getMessage("faile.path", null, appProperties.getLocale());
    List<Task> tasks = new ArrayList<>();
    try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
      String[] nextRecord;
      // skip the first cell
      csvReader.readNext();
      while ((nextRecord = csvReader.readNext()) != null) {
        for (String cell : nextRecord) {
          String[] data = cell.split(";");

          tasks.add(Task.builder()
              .id(Integer.parseInt(data[0]))
              .question(data[1])
              .option(Arrays.asList(data[2], data[3], data[4], data[5]))
              .answer(data[6].trim())
              .build());
        }
      }
    } catch (IOException e) {
      logger.error("Ошибка при работе с файлом", e);
    }

    return tasks;
  }

  public Task getById(Integer id) {
    // пока не надо
    return null;
  }

}
