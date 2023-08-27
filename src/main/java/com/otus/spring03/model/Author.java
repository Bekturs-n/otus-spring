package com.otus.spring03.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * More about lombok @see <a href="https://habr.com/ru/companies/haulmont/articles/564682/">Lombok with JPA</a>
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "author")
public class Author {

  @Id
  private long id;
  private String author;
  private String surname;

}
