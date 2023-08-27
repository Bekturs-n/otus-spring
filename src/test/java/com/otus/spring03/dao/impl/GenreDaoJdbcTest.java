package com.otus.spring03.dao.impl;

import com.otus.spring03.model.Genre;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * More information  - @see<a href="https://java-ru-blog.blogspot.com/2020/04/spring-boot-data-jpa-tests.html">@DataJpaTest</a>
 */
@DataJpaTest
@Import(GenreDaoImpl.class)
class GenreDaoJdbcTest {

  private static final int EXPECTED_LIST_SIZE = 3;
  private static final String GENRE_NAME = "Novel";
  
  @Autowired
  private GenreDaoImpl genreDaoJdbcImpl;
  @Autowired
  private TestEntityManager tem;
  
  @Test
  void getById() {
    val actual = genreDaoJdbcImpl.findById(1);
    val expected = tem.find(Genre.class, 1L);

    assertThat(actual).isPresent().get()
        .usingRecursiveComparison().isEqualTo(expected);
  }

  @Test
  void findByName() {
    val actual = genreDaoJdbcImpl.findByName(GENRE_NAME);
    val expected = tem.find(Genre.class, 1L);

    assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
  }

  @Test
  void delete() {
    val genre = genreDaoJdbcImpl.findById(1L);
    genreDaoJdbcImpl.delete(genre.get());

    val expected = genreDaoJdbcImpl.findById(1L);
    assertFalse(expected.isPresent());
  }

  @Test
  void insert() {
    val actual = genreDaoJdbcImpl.insert(Genre.builder().genre(GENRE_NAME).build());
    val expected = tem.find(Genre.class, actual.getId());

    assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
  }

  @Test
  void update() {
    val genre = tem.find(Genre.class, 1L);
    genre.setGenre(GENRE_NAME);
    genreDaoJdbcImpl.update(genre);
    val actual = genreDaoJdbcImpl.findByName(GENRE_NAME);

    assertNotNull(actual);
    assertEquals(GENRE_NAME, actual.getGenre());
  }


  @Test
  void findAll() {
    List<Genre> authorList =  genreDaoJdbcImpl.findAll();

    assertThat(authorList).isNotNull().hasSize(EXPECTED_LIST_SIZE)
        .allMatch(s -> !s.getGenre().equals(""))
        .allMatch(s -> s.getBooks() != null && s.getBooks().size() > 0);
  }
}