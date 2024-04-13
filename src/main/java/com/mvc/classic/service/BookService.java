package com.mvc.classic.service;

import com.mvc.classic.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
  Book save(Book book);

  List<Book> getAll();

  Optional<Book> getBy(long id);

  void updateBook(long bookId, String newBookName);

  void removeBy(long id);

  Book getByName(String name);

  String checkAndSave(String bookName, String authorName, String comment, String[] genreNames);
}
