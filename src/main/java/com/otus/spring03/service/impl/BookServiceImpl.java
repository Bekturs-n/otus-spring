package com.otus.spring03.service.impl;

import com.otus.spring03.dao.BookDao;
import com.otus.spring03.model.Author;
import com.otus.spring03.model.Book;
import com.otus.spring03.model.Comment;
import com.otus.spring03.model.Genre;
import com.otus.spring03.service.AuthorService;
import com.otus.spring03.service.BookService;
import com.otus.spring03.service.CommentService;
import com.otus.spring03.service.GenreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

  private final BookDao bookDaoJdbc;
  private final AuthorService authorService;
  private final GenreService genreService;
  private final CommentService commentService;

  @Override
  public Book save(String bookName) {
    Book book = Book.builder().bookName(bookName).build();
    book.setId(getNextId());
    return bookDaoJdbc.save(book);
  }

  @Override
  public List<Book> getAll() {
    List<Book> list = bookDaoJdbc.findAll();
    if (list == null) {
      log.warn("DataBase is empty ");
      return null;
    }
    return list;
  }

  @Override
  public Book getBy(long id) {
    Optional<Book> book = bookDaoJdbc.findById(id);
    if (book.isPresent()) {
      return book.get();
    } else {
      log.info("No book with this credentials");
      return null;
    }
  }

  @Override
  public void updateBook(String oldBookName, String newBookName) {
    Optional<Book> optionalBook = bookDaoJdbc.findByBookName(oldBookName);
    if (optionalBook.isEmpty()) {
      log.error("No book with this ID in Database, please write correct id");
      return;
    }
    Book book = optionalBook.get();
    book.setBookName(newBookName);
    bookDaoJdbc.save(book);
  }

  @Override
  public void removeBy(long id) {
    bookDaoJdbc.deleteById(id);
  }

  @Override
  public Book getByName(String bookName) {
    Optional<Book> book = bookDaoJdbc.findByBookName(bookName);
    if (book.isEmpty()) {
      log.info("No book with this credentials");
      return null;
    }

    return book.get();
  }

  @Override
  public List<Book> getByGenre(String genreName) {
    Genre genre = genreService.getBy(genreName);
    if (genre == null) {
      log.warn("Nothing with this credentials");
      return Collections.emptyList();
    }
    return bookDaoJdbc.findAll().stream().filter(book -> book.getGenreIds().contains(genre.getId())).toList();
  }

  @Override
  public List<Book> getByAuthor(String authorName) {
    List<Book> books = bookDaoJdbc.findBookByAuthor_Author(authorName);
    if (books == null) {
      log.warn("Nothing with this credentials");
      return Collections.emptyList();
    }
    return bookDaoJdbc.findBookByAuthor_Author(authorName);
  }

  @Override
  public String checkAndSave(String bookName, String authorName, String commentString, String[] genreNames) {
    Optional<Book> optionalBook = bookDaoJdbc.findByBookName(bookName);
    if (optionalBook.isPresent()) {
      log.info("We have book in library with this credentials");
      return "This book we have in DB";
    }
    List<Genre> genres = Arrays.stream(genreNames).map(elem -> Genre.builder().genre(elem).build()).toList();
    genreService.saveMoreByName(genres);

    Author author = authorService.getOrCreateAuthor(authorName);

    Book book = Book.builder().build();
    List<Long> genreList = new ArrayList<>();
    for (Genre genre : genres) {
      genreList.add(genreService.getBy(genre.getGenre()).getId());
    }
    book.setBookName(bookName);
    book.setAuthor(author);
    book.setGenreIds(genreList);
    book.setId(getNextId());
    book = bookDaoJdbc.save(book);

    Comment comment = Comment.builder().comment(commentString).bookId(book.getId()).build();
    commentService.save(comment);
    return "New book saved ";
  }

  private long getNextId() {
    Book book = bookDaoJdbc.findFirstByOrderByIdDesc();
    return book == null ? 1 : book.getId() + 1;
  }
}
