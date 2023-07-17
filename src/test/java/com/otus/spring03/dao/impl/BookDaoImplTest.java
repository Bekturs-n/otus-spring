package com.otus.spring03.dao.impl;

import com.otus.spring03.model.Author;
import com.otus.spring03.model.Book;
import com.otus.spring03.model.Comment;
import com.otus.spring03.model.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@Import(BookDaoImpl.class)
class BookDaoImplTest {

  private static final String BOOK_NAME = "Musketeer";

  @Autowired
  private BookDaoImpl bookDao;

  @Autowired
  private TestEntityManager tem;

  @Test
  void insert() {
    Author author = tem.find(Author.class, 1L);
    Book actualBook = Book.builder()
        .bookName("Name")
        .author(author).build();

    actualBook = bookDao.insert(actualBook);
    Book expected = tem.find(Book.class, actualBook.getId());

    assertThat(actualBook).usingRecursiveComparison().isEqualTo(expected);
  }

  @Test
  void testUpdateAndFindById() {
    String newBookName = BOOK_NAME + " twenty years after";
    Book actualBook = bookDao.findByName(BOOK_NAME);
    actualBook.setBookName(newBookName);

    actualBook = bookDao.update(actualBook);
    Book expected = tem.find(Book.class, 1L);

    assertThat(actualBook).usingRecursiveComparison().isEqualTo(expected);
  }

    @Test
    void deleteById() {
    Book book = tem.find(Book.class, 1L);
    bookDao.delete(book);

    assertNull(tem.find(Book.class, 1L));
    }
}