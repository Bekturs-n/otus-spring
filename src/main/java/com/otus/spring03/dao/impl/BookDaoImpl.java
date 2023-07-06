package com.otus.spring03.dao.impl;

import com.otus.spring03.dao.AuthorDaoJdbc;
import com.otus.spring03.dao.BookDaoJdbc;
import com.otus.spring03.dao.GenreDaoJdbc;
import com.otus.spring03.model.Author;
import com.otus.spring03.model.Book;
import com.otus.spring03.model.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BookDaoImpl implements BookDaoJdbc {

  private final NamedParameterJdbcOperations jdbc;
  private final AuthorDaoJdbc authorDaoJdbc;
  private final GenreDaoJdbc genreDaoJdbc;

  @Override
  public void insert(Book book) {
    jdbc.update("INSERT INTO books(bookName, author_id, genre_id) VALUES(:bookName, :author_id, :genre_id)",
        Map.of("bookName", book.getBookName(), "author_id", book.getAuthor().getId(),
            "genre_id", book.getGenre().getId()));
  }

  @Override
  public void update(Book book) {
    jdbc.update("UPDATE books SET bookName = :bookName, author_id = :authorId, genre_id = :genreId",
        Map.of("bookName", book.getBookName(), "authorId", book.getAuthor().getId(),
            "genreId", book.getGenre().getId()));
  }

  @Override
  public void deleteById(long id) {
    jdbc.update("DELETE FROM books WHERE id = :id", Map.of("id", id));
  }

  @Override
  public Book getById(long id) {
    Book book = null;
    try {
      book = jdbc.queryForObject("SELECT b.id, b.bookName, b.author_id, b.genre_id FROM books AS b WHERE id = :id",
          Map.of("id", id), new BookMapper());
    } catch (EmptyResultDataAccessException e) {
    }
    return book;
  }

  @Override
  public Book getByName(String name) {
    Book book = null;
    try {
      book = jdbc.queryForObject(
          "SELECT b.id, b.bookName, b.author_id, b.genre_id FROM books as b WHERE bookName = :name",
          Map.of("name", name), new BookMapper());
    } catch (EmptyResultDataAccessException e) {
    }
    return book;
  }

  @Override
  public List<Book> getAll() {
    List<Book> list = null;
    try {
      list = jdbc.query("SELECT b.id, b.bookName, b.author_id, b.genre_id FROM books AS b",
          new RowMapper<Book>() {
            public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
              Book book = new Book();
              book.setId(rs.getLong(1));
              book.setBookName(rs.getString(2));
              book.setAuthor(Author.builder().id(Long.parseLong(rs.getString(3))).build());
              book.setGenre(Genre.builder().id(Long.parseLong(rs.getString(4))).build());
              return book;
            }
          });
    } catch (DataAccessException e) {
    }
    return list;
  }

  class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
      long id = rs.getLong("id");
      String bookName = rs.getString("bookName");
      Author author = authorDaoJdbc.getById(rs.getLong("author_id"));
      Genre genre = genreDaoJdbc.getById(rs.getLong("genre_id"));
      return new Book(id, bookName, author, genre);
    }
  }
}
