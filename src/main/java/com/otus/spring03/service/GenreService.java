package com.otus.spring03.service;

import com.otus.spring03.model.Genre;

public interface GenreService {

  long count();

  void save(Genre genre);

  Genre getById(long id);

  Genre getByName(String genreName);

  Genre getOrCreateGenre(String genreName);

  void update(Genre genre);

  void removeById(long id);
}
