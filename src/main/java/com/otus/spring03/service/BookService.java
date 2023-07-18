package com.otus.spring03.service;

import com.otus.spring03.model.Book;

import java.util.List;

public interface BookService {
  Book save(Book book);

  List<Book> getAll();

  Book getBy(long id);

  void updateBook(long bookId, String newBookName);

  void removeBy(long id);

  Book getByName(String name);

  String checkAndSave(String bookName, String authorName, String comment, String[] genreNames);
}
