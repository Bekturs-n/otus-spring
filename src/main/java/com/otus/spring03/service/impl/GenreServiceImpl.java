package com.otus.spring03.service.impl;

import com.otus.spring03.dao.GenreDao;
import com.otus.spring03.model.Genre;
import com.otus.spring03.service.GenreService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Log4j2
@Component
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

  private final GenreDao genreDaoJdbc;

  @Override
  @Transactional
  public long count() {
    return genreDaoJdbc.findAll().size();
  }

  @Override
  @Transactional
  public void save(Genre genre) {
    genreDaoJdbc.insert(genre);
  }

  @Override
  @Transactional
  public Genre getBy(long id) {
    Optional<Genre> optionalGenre = genreDaoJdbc.findById(id);
    if (optionalGenre.isEmpty()) {
      log.info("No genre with this credentials");
      return null;
    }

    return optionalGenre.get();
  }

  @Override
  @Transactional
  public Genre getBy(String genreName) {
    Genre genre;
    try {
      genre = genreDaoJdbc.findByName(genreName);
    } catch (EmptyResultDataAccessException e) {
      log.info("No genre with this credentials");
      return null;
    }
    return genre;
  }

  @Override
  @Transactional
  public void update(Genre genre) {
    genreDaoJdbc.update(genre);
  }

  @Override
  @Transactional
  public void removeBy(long id) {
    Optional<Genre> optionalGenre = genreDaoJdbc.findById(id);
    if (optionalGenre.isEmpty()) {
      log.info("No genre with this credentials, nothing has been deleted");
      return;
    }
    genreDaoJdbc.delete(optionalGenre.get());
  }

  @Override
  @Transactional
  public Genre getOrCreateGenre(String genreName) {
    Genre genre;
    try {
      genre = genreDaoJdbc.findByName(genreName);
    } catch (EmptyResultDataAccessException e) {
      return genreDaoJdbc.insert(Genre.builder().genre(genreName).build());
    }
    return genre;
  }

  @Override
  @Transactional
  public List<Genre> getAll() {
    return genreDaoJdbc.findAll();
  }

  @Override
  @Transactional
  public void saveMoreByName(List<Genre> genreList) {
    for (Genre genre : genreList) {
      try {
        Genre genreFromDB = genreDaoJdbc.findByName(genre.getGenre());
        genreFromDB.setGenre(genre.getGenre());
        genreDaoJdbc.update(genreFromDB);
      } catch (EmptyResultDataAccessException e) {
        genreDaoJdbc.insert(genre);
      }
    }
  }
}
