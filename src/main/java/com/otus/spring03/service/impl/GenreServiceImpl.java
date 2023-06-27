package com.otus.spring03.service.impl;

import com.otus.spring03.dao.GenreDao;
import com.otus.spring03.model.Author;
import com.otus.spring03.model.Genre;
import com.otus.spring03.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

  private final GenreDao genreDao;

  @Override
  public long count() {
    return genreDao.count();
  }

  @Override
  public void save(Genre genre) {
    genreDao.insert(genre);
  }

  @Override
  public Genre getById(long id) {
    return genreDao.getById(id);
  }

  @Override
  public Genre getByName(String genreName) {
    return genreDao.getByName(genreName);
  }

  @Override
  public void update(Genre genre) {
    genreDao.update(genre);
  }

  @Override
  public void removeById(long id) {
    genreDao.deleteById(id);
  }

  @Override
  public Genre getOrCreateGenre(String genreName) {
    Genre genre = genreDao.getByName(genreName);
    if (genre == null) {
      genre = Genre.builder().genre(genreName).build();
      genre.setId(genreDao.count() + 1);
      genreDao.insert(genre);
    }

    return genre;
  }
}
