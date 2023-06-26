package com.otus.spring.writer;

import java.io.IOException;

public interface CsvWriter {

  boolean writeData(String userName, Integer correctAnswer, String result) throws IOException;

}
