package com.otus.spring03.dao;

import com.otus.spring03.model.Genre;

public interface GenreDaoJdbc {

  long count();

  void insert(Genre genre);

  void update(Genre genre);

  void deleteById(long id);

  Genre getById(long id);

  Genre getByName(String genreName);
}
