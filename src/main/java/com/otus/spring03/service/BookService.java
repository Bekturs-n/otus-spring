package com.otus.spring03.service;

import com.otus.spring03.model.Book;

import java.util.List;

public interface BookService {
  Book save(String bookName);

  List<Book> getAll();

  Book getBy(long id);

  void updateBook(String oldBookName, String newBookName);

  void removeBy(long id);

  Book getByName(String name);

  List<Book> getByGenre(String genreName);

  List<Book> getByAuthor(String authorName);

  String checkAndSave(String bookName, String authorName, String comment, String[] genreNames);
}
