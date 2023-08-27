package com.otus.spring03.service;

import com.otus.spring03.model.Genre;

import java.util.List;

public interface GenreService {
  long count();

  void save(Genre genre);

  Genre getBy(long id);

  Genre getBy(String genreName);

  void update(Genre genre);

  void removeBy(long id);

  Genre getOrCreateGenre(String genreName);

  List<Genre> getAll();

  void saveMoreByName(List<Genre> genre);

}
