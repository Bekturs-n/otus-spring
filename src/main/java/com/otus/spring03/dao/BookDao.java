package com.otus.spring03.dao;

import com.otus.spring03.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
  Book insert(Book book);

  Optional<Book> findById(long id);

  Book findByName(String name);

  Book update(Book book);

  void delete(Book book);

  List<Book> findAll();
}
