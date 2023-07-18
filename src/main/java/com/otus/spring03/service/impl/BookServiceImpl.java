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
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
  @Transactional
  public Book save(Book book) {
    return bookDaoJdbc.save(book);
  }

  @Override
  @Transactional
  public List<Book> getAll() {
    List<Book> list = bookDaoJdbc.findAll();
    if (list == null) {
      log.warn("DataBase is empty ");
      return null;
    }
    return list;
  }

  @Override
  @Transactional
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
  @Transactional
  public void updateBook(long bookId, String newBookName) {
    Optional<Book> optionalBook = bookDaoJdbc.findById(bookId);
    if (optionalBook.isEmpty()) {
      log.error("No book with this ID in Database, please write correct id");
      return;
    }
    Book book = optionalBook.get();
    book.setBookName(newBookName);
    bookDaoJdbc.save(book);
  }

  @Override
  @Transactional
  public void removeBy(long id) {
    bookDaoJdbc.deleteById(id);
  }

  @Override
  @Transactional
  public Book getByName(String bookName) {
    Optional<Book> book = bookDaoJdbc.findByBookName(bookName);
    if (book.isEmpty()) {
      log.info("No book with this credentials");
      return null;
    }

    return book.get();
  }

  @Override
  @Transactional
  public String checkAndSave(String bookName, String authorName, String commentString, String[] genreNames) {
    Optional<Book> optionalBook = bookDaoJdbc.findByBookName(bookName);
    if (optionalBook.isPresent()) {
      log.info("We have book with this credentials");
      return "This book we have in DB";
    }
    List<Genre> genres = Arrays.stream(genreNames).map(elem -> Genre.builder().genre(elem).build()).toList();
    genreService.saveMoreByName(genres);

    Author author = Author.builder().author(authorName).build();
    author = authorService.save(author);

    Book book = Book.builder().genre(genres).bookName(bookName).author(author).build();
    book = bookDaoJdbc.save(book);

    Comment comment = Comment.builder().comment(commentString).book(book).build();
    commentService.save(comment);
    return "New book saved ";
  }
}
