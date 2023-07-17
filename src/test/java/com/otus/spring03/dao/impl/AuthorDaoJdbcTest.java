package com.otus.spring03.dao.impl;

import com.otus.spring03.model.Author;
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
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * More information  - @see<a href="https://java-ru-blog.blogspot.com/2020/04/spring-boot-data-jpa-tests.html">@DataJpaTest</a>
 */
@DataJpaTest
@Import(AuthorDaoImpl.class)
class AuthorDaoJdbcTest {

  private static final int EXPECTED_LIST_SIZE = 1;
  private static final String AUTHOR_NAME = "Alexandre";

  @Autowired
  private AuthorDaoImpl authorDaoJPAImpl;

  @Autowired
  private TestEntityManager tem;

  @Test
  void getById() {
    val actual = authorDaoJPAImpl.findById(1);
    val expected = tem.find(Author.class, 1L);

    assertThat(actual).isPresent().get()
        .usingRecursiveComparison().isEqualTo(expected);
  }

  @Test
  void findByName() {
    val actual = authorDaoJPAImpl.findByName(AUTHOR_NAME);
    val expected = tem.find(Author.class, 1L);

    assertThat(actual).isPresent().get()
        .usingRecursiveComparison().isEqualTo(expected);
  }

  @Test
  void delete() {
    val author = authorDaoJPAImpl.findById(1L);
    authorDaoJPAImpl.delete(author.get());

    val expected = authorDaoJPAImpl.findById(1L);
    assertFalse(expected.isPresent());

  }

  @Test
  void insert() {
    val actual = authorDaoJPAImpl.insert(Author.builder().author(AUTHOR_NAME).build());
    val expected = tem.find(Author.class, actual.getId());

    assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
  }

  @Test
  void update() {
    val author = tem.find(Author.class, 1L);
    author.setAuthor(AUTHOR_NAME);
    authorDaoJPAImpl.update(author);
    val actual = authorDaoJPAImpl.findByName(AUTHOR_NAME);

    assertTrue(actual.isPresent());
    assertEquals(AUTHOR_NAME, actual.get().getAuthor());
  }


  @Test
  void findAll() {
    List<Author> authorList =  authorDaoJPAImpl.findAll();

    assertThat(authorList).isNotNull().hasSize(EXPECTED_LIST_SIZE)
        .allMatch(s -> !s.getAuthor().equals(""))
        .allMatch(s -> !s.getSurname().equals(""))
        .allMatch(s -> s.getBooks() != null && s.getBooks().size() > 0);
  }

}