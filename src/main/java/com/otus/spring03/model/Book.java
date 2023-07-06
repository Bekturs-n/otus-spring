package com.otus.spring03.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {
  private long id;
  private String bookName;
  private Author author;
  private Genre genre;
}
