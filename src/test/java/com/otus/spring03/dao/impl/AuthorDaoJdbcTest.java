package com.otus.spring03.dao.impl;

import com.otus.spring03.model.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@JdbcTest
@Import(AuthorDaoJdbc.class)
class AuthorDaoJdbcTest {

  @Autowired
  private AuthorDaoJdbc authorDaoJdbc;

  @Test
  void insert() {
    Author author = Author.builder().author("SomeName").surname("").build();
    authorDaoJdbc.insert(author);
    author = authorDaoJdbc.getByName(author.getAuthor());

    assertNotNull(author);
    authorDaoJdbc.deleteById(author.getId());
  }

  @Test
  void update() {
    Author author = Author.builder().author("SomeName").surname("").build();
    authorDaoJdbc.insert(author);
    author = authorDaoJdbc.getByName(author.getAuthor());
    author.setSurname("Some2");
    authorDaoJdbc.update(author);

    assertEquals("Some2", authorDaoJdbc.getById(author.getId()).getSurname());
    authorDaoJdbc.deleteById(author.getId());
  }

  @Test
  void deleteById() {
    Author author = Author.builder().author("SomeName").surname("").build();
    authorDaoJdbc.insert(author);
    author = authorDaoJdbc.getByName(author.getAuthor());

    authorDaoJdbc.deleteById(author.getId());
    assertNull(authorDaoJdbc.getById(author.getId()));
  }

  @Test
  void getById() {
    Author author = Author.builder().author("SomeName").surname("").build();
    authorDaoJdbc.insert(author);
    author = authorDaoJdbc.getByName(author.getAuthor());

    assertEquals("SomeName", authorDaoJdbc.getById(author.getId()).getAuthor());
    authorDaoJdbc.deleteById(author.getId());
  }
}