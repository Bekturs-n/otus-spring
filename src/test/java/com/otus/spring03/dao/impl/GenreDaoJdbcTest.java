package com.otus.spring03.dao.impl;

import com.otus.spring03.model.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@JdbcTest
@Import(GenreDaoJdbc.class)
class GenreDaoJdbcTest {

  @Autowired
  private GenreDaoJdbc genreDaoJdbc;

  @Test
  void insert() {
    Genre genre = Genre.builder().genre("Genre").build();
    genreDaoJdbc.insert(genre);
    genre = genreDaoJdbc.getByName("Genre");

    assertNotNull(genre.getGenre());
    genreDaoJdbc.deleteById(genre.getId());
  }

  @Test
  void update() {
    Genre genre = Genre.builder().genre("Genre").build();
    genreDaoJdbc.insert(genre);
    genre = genreDaoJdbc.getByName("Genre");
    genre.setGenre("AnotherGenre");
    genreDaoJdbc.update(genre);

    assertEquals("AnotherGenre", genreDaoJdbc.getById(genre.getId()).getGenre());
    genreDaoJdbc.deleteById(genre.getId());
  }

  @Test
  void deleteById() {
    Genre genre = Genre.builder().genre("Genre").build();
    genreDaoJdbc.insert(genre);
    genre = genreDaoJdbc.getByName("Genre");

    genreDaoJdbc.deleteById(genre.getId());
    assertNull(genreDaoJdbc.getById(genre.getId()));
  }

  @Test
  void getByName() {
    Genre genre = Genre.builder().genre("Genre").build();
    genreDaoJdbc.insert(genre);
    genre = genreDaoJdbc.getByName("Genre");

    assertNotNull(genre);
    genreDaoJdbc.deleteById(genre.getId());

  }
}