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
@Import(GenreDaoJdbcImpl.class)
class GenreDaoJdbcTest {

  @Autowired
  private GenreDaoJdbcImpl genreDaoJdbcImpl;

  @Test
  void insert() {
    Genre genre = Genre.builder().genre("Genre").build();
    genreDaoJdbcImpl.insert(genre);
    genre = genreDaoJdbcImpl.getByName("Genre");

    assertNotNull(genre.getGenre());
    genreDaoJdbcImpl.deleteById(genre.getId());
  }

  @Test
  void update() {
    Genre genre = Genre.builder().genre("Genre").build();
    genreDaoJdbcImpl.insert(genre);
    genre = genreDaoJdbcImpl.getByName("Genre");
    genre.setGenre("AnotherGenre");
    genreDaoJdbcImpl.update(genre);

    assertEquals("AnotherGenre", genreDaoJdbcImpl.getById(genre.getId()).getGenre());
    genreDaoJdbcImpl.deleteById(genre.getId());
  }

  @Test
  void deleteById() {
    Genre genre = Genre.builder().genre("Genre").build();
    genreDaoJdbcImpl.insert(genre);
    genre = genreDaoJdbcImpl.getByName("Genre");

    genreDaoJdbcImpl.deleteById(genre.getId());
    assertNull(genreDaoJdbcImpl.getById(genre.getId()));
  }

  @Test
  void getByName() {
    Genre genre = Genre.builder().genre("Genre").build();
    genreDaoJdbcImpl.insert(genre);
    genre = genreDaoJdbcImpl.getByName("Genre");

    assertNotNull(genre);
    genreDaoJdbcImpl.deleteById(genre.getId());

  }
}