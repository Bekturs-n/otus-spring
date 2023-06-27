package com.otus.spring03.dao;

import com.otus.spring03.model.Book;

public interface BookDao {

  void insert(Book book);

  void update(Book book);

  void deleteById(long id);

  Book getById(long id);

  Book getByName(String name);

}
