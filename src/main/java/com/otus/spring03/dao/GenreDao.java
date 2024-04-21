package com.otus.spring03.dao;

import com.otus.spring03.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {
  Genre insert(Genre genre);

  Optional<Genre> findById(long id);

  void update(Genre genre);

  void delete(Genre genre);

  List<Genre> findAll();

  Genre findByName(String genre);
}
