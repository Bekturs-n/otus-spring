package com.otus.spring03.dao.impl;

import com.otus.spring03.model.Author;
import com.otus.spring03.model.Book;
import com.otus.spring03.model.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@JdbcTest
@Import({ BookDaoImpl.class, AuthorDaoJdbcImpl.class, GenreDaoJdbcImpl.class })
class BookDaoImplTest {

  @Autowired
  private BookDaoImpl bookDao;
  @Autowired
  private AuthorDaoJdbcImpl authorDaoJdbcImpl;
  @Autowired
  private GenreDaoJdbcImpl genreDaoJdbcImpl;

  private Book book;
  private Author author;
  private Genre genre;

  @Test
  void insert() {
    createData(2);
    authorDaoJdbcImpl.insert(author);
    genreDaoJdbcImpl.insert(genre);
    bookDao.insert(book);

    assertNotNull(bookDao.getById(2));
    bookDao.deleteById(2);
  }

  @Test
  void update() {
    createData(3);
    authorDaoJdbcImpl.insert(author);
    genreDaoJdbcImpl.insert(genre);
    bookDao.insert(book);
    book.setBookName("AnotherBook");
    bookDao.update(book);

    assertEquals("AnotherBook", bookDao.getById(3).getBookName());

    bookDao.deleteById(2);
  }

  @Test
  void deleteById() {
    createData(4);

    authorDaoJdbcImpl.insert(author);
    genreDaoJdbcImpl.insert(genre);
    bookDao.insert(book);

    bookDao.deleteById(3);
    assertNull(bookDao.getById(3));
  }

  private void createData(long id) {
    author = new Author(id, "SomeName", "SomeSurname");
    genre = new Genre(id, "SomeGenre");
    book = new Book(id, "Book", author, genre);
  }
}