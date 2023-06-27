package com.otus.spring.writer.impl;

import com.opencsv.CSVWriter;
import com.otus.spring.config.AppProperties;
import com.otus.spring.writer.CsvWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CsvWriterImpl implements CsvWriter {

  private final AppProperties appProperties;
  private final MessageSource messageSource;

  @Override
  public boolean writeData(String userName, Integer correctAnswer, String result) throws IOException {
    String absolutePath = new File("").getAbsolutePath();
    String resultPath = messageSource.getMessage("result.path", null, appProperties.getLocale());

    try (FileWriter writer = new FileWriter(absolutePath + resultPath, true);
        CSVWriter csvWriter = new CSVWriter(writer,
            ';',
            CSVWriter.NO_QUOTE_CHARACTER,
            CSVWriter.DEFAULT_ESCAPE_CHARACTER,
            CSVWriter.DEFAULT_LINE_END)) {

      csvWriter.writeNext(new String[] { userName, correctAnswer.toString(), result });
    }

    return true;
  }
}
