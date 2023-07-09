package com.otus.spring.reader;

import java.util.List;

public interface CsvReader {

  List<List<String>> readAllFile();

  List<String> readCellByRowNumber(Integer rowNumber);

}
