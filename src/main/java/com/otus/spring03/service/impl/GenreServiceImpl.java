package com.otus.spring03.service.impl;

import com.otus.spring03.dao.GenreDaoJdbc;
import com.otus.spring03.model.Genre;
import com.otus.spring03.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

  private final GenreDaoJdbc genreDaoJdbc;

  @Override
  public long count() {
    return genreDaoJdbc.count();
  }

  @Override
  public void save(Genre genre) {
    genreDaoJdbc.insert(genre);
  }

  @Override
  public Genre getById(long id) {
    return genreDaoJdbc.getById(id);
  }

  @Override
  public Genre getByName(String genreName) {
    return genreDaoJdbc.getByName(genreName);
  }

  @Override
  public void update(Genre genre) {
    genreDaoJdbc.update(genre);
  }

  @Override
  public void removeById(long id) {
    genreDaoJdbc.deleteById(id);
  }

  @Override
  public Genre getOrCreateGenre(String genreName) {
    Genre genre = genreDaoJdbc.getByName(genreName);

    if (genre == null) {
      genre = Genre.builder().genre(genreName).build();
      genre.setId(genreDaoJdbc.count() + 1);
      genreDaoJdbc.insert(genre);
    }

    return genre;
  }
}
