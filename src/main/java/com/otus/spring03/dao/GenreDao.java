package com.otus.spring03.dao;

import com.otus.spring03.model.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GenreDao extends CrudRepository<Genre, Long> {

  @Override
  List<Genre> findAll();

  Optional<Genre> findByGenre(String genre);

  Genre findFirstByOrderByIdDesc();
}
