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
@Import(AuthorDaoJdbcImpl.class)
class AuthorDaoJdbcTest {

  @Autowired
  private AuthorDaoJdbcImpl authorDaoJdbcImpl;

  @Test
  void insert() {
    Author author = Author.builder().author("SomeName").surname("").build();
    authorDaoJdbcImpl.insert(author);
    author = authorDaoJdbcImpl.getByName(author.getAuthor());

    assertNotNull(author);
    authorDaoJdbcImpl.deleteById(author.getId());
  }

  @Test
  void update() {
    Author author = Author.builder().author("SomeName").surname("").build();
    authorDaoJdbcImpl.insert(author);
    author = authorDaoJdbcImpl.getByName(author.getAuthor());
    author.setSurname("Some2");
    authorDaoJdbcImpl.update(author);

    assertEquals("Some2", authorDaoJdbcImpl.getById(author.getId()).getSurname());
    authorDaoJdbcImpl.deleteById(author.getId());
  }

  @Test
  void deleteById() {
    Author author = Author.builder().author("SomeName").surname("").build();
    authorDaoJdbcImpl.insert(author);
    author = authorDaoJdbcImpl.getByName(author.getAuthor());

    authorDaoJdbcImpl.deleteById(author.getId());
    assertNull(authorDaoJdbcImpl.getById(author.getId()));
  }

  @Test
  void getById() {
    Author author = Author.builder().author("SomeName").surname("").build();
    authorDaoJdbcImpl.insert(author);
    author = authorDaoJdbcImpl.getByName(author.getAuthor());

    assertEquals("SomeName", authorDaoJdbcImpl.getById(author.getId()).getAuthor());
    authorDaoJdbcImpl.deleteById(author.getId());
  }
}