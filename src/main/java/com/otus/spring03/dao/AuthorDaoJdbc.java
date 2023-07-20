package com.otus.spring03.dao;

import com.otus.spring03.model.Author;

public interface AuthorDaoJdbc {

  long count();

  void insert(Author author);

  void update(Author author);

  void deleteById(long id);

  Author getById(long id);

  Author getByName(String authorName);
}
