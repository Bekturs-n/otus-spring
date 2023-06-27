package com.otus.spring03.service;

import com.otus.spring03.model.Book;

public interface BookService {

  void saveNewBook(Book book);

  Book getBookById(long id);

  Book getBookByName(String name);

  void updateBook(Integer bookId, String newBookName, String newAutorName, String newAuthorSurname,
      String newGenreName);

  void deleteBookById(long id);

  String checkAndSaveBook(String bookName, String autor, String genre);

}
