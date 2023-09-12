package com.mvc.classic.repository;

import com.mvc.classic.domain.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GenreDao extends CrudRepository<Genre, Long> {

  @Override
  List<Genre> findAll();

  Optional<Genre> findByGenre(String genre);
}
