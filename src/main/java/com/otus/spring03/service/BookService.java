package com.otus.spring03.service;

import com.otus.spring03.model.Book;

import java.util.List;

public interface BookService {

  void saveNewBook(Book book);

  List<Book> getAll();

  Book getBookBy(long id);

  Book getBookBy(String name);

  void updateBook(Integer bookId, String newBookName, String newAutorName, String newAuthorSurname,
      String newGenreName);

  void deleteBookById(long id);

  String checkAndSaveBook(String bookName, String autor, String genre);

}
