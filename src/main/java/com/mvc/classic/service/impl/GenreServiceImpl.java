package com.mvc.classic.service.impl;

import com.mvc.classic.repository.GenreDao;
import com.mvc.classic.domain.Genre;
import com.mvc.classic.service.GenreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Log4j2
@Component
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

  private final GenreDao genreDaoJdbc;

  @Override
  public long count() {
    return genreDaoJdbc.findAll().size();
  }

  @Override
  public void save(Genre genre) {
    genreDaoJdbc.save(genre);
  }

  @Override
  public Genre getBy(long id) {
    Optional<Genre> optionalGenre = genreDaoJdbc.findById(id);
    if (optionalGenre.isEmpty()) {
      log.info("No genre with this credentials");
      return null;
    }
    return optionalGenre.get();
  }

  @Override
  public Genre getBy(String genreName) {
    Optional<Genre> genre = genreDaoJdbc.findByGenre(genreName);
    if (genre.isEmpty()) {
      log.info("No genre with this credentials");
      return null;
    }
    return genre.get();
  }

  @Override
  public void update(Genre genre) {
    Optional<Genre> optional = genreDaoJdbc.findById(genre.getId());
    if (optional.isEmpty()) {
      log.warn("No genre");
      return;
    }
    Genre genreForUpdate = optional.get();
    genreForUpdate.setGenre(genre.getGenre());
    genreDaoJdbc.save(genreForUpdate);
  }

  @Override
  public void removeBy(long id) {
    genreDaoJdbc.deleteById(id);
  }

  @Override
  public List<Genre> getAll() {
    return genreDaoJdbc.findAll();
  }

  @Override
  public void saveMoreByName(List<Genre> genreList) {
    for (Genre genre : genreList) {
      Optional<Genre> genreFromDB = genreDaoJdbc.findByGenre(genre.getGenre());
      if (genreFromDB.isEmpty()) {
        genreDaoJdbc.save(genre);
      }
    }
  }
}
