package com.mvc.classic.service;

import com.mvc.classic.domain.Genre;

import java.util.List;

public interface GenreService {
  long count();

  void save(Genre genre);

  Genre getBy(long id);

  Genre getBy(String genreName);

  void update(Genre genre);

  void removeBy(long id);

  List<Genre> getAll();

  void saveMoreByName(List<Genre> genre);

}
