package com.otus.spring03.service.impl;

import com.otus.spring03.dao.impl.GenreDaoJdbcImpl;
import com.otus.spring03.model.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GenreServiceImplTest {

  @Mock
  private GenreDaoJdbcImpl genreDaoJdbcImpl;

  private GenreServiceImpl genreService;

  @BeforeEach
  public void beforeEach(){
    genreService = new GenreServiceImpl(genreDaoJdbcImpl);
  }

  @Test
  void count() {
    when(genreDaoJdbcImpl.count()).thenReturn(1L);
    assertEquals(1L, genreService.count());
  }

  @Test
  void save() {
    doNothing().when(genreDaoJdbcImpl).insert(any());
    genreService.save(any());
    verify(genreDaoJdbcImpl).insert(any());
  }

  @Test
  void getById() {
    Genre genre = Genre.builder().build();
    when(genreDaoJdbcImpl.getById(1)).thenReturn(genre);

    assertEquals(genre, genreService.getById(1));
    verify(genreDaoJdbcImpl).getById(1);
  }

  @Test
  void getByName() {
    Genre genre = Genre.builder().build();
    when(genreDaoJdbcImpl.getByName(anyString())).thenReturn(genre);

    assertEquals(genre, genreService.getByName(anyString()));
    verify(genreDaoJdbcImpl).getByName(anyString());
  }

  @Test
  void update() {
    doNothing().when(genreDaoJdbcImpl).update(any());
    genreService.update(any());
    verify(genreDaoJdbcImpl).update(any());
  }

  @Test
  void removeById() {
    doNothing().when(genreDaoJdbcImpl).deleteById(anyLong());
    genreService.removeById(anyLong());
    verify(genreDaoJdbcImpl).deleteById(anyLong());
  }

  @Test
  void getOrCreateGenreWhenGenreContains() {
    Genre genre = Genre.builder().build();
    when(genreDaoJdbcImpl.getByName(anyString())).thenReturn(genre);
    assertEquals(genre, genreService.getOrCreateGenre(anyString()));
    verify(genreDaoJdbcImpl).getByName(anyString());
  }

  @Test
  void getOrCreateGenreWhenGenreNotContains() {
    String genre = "Genre";
    when(genreDaoJdbcImpl.getByName(genre)).thenReturn(null);

    assertEquals(genre, genreService.getOrCreateGenre(genre).getGenre());
    verify(genreDaoJdbcImpl).getByName(anyString());
    verify(genreDaoJdbcImpl).count();
    verify(genreDaoJdbcImpl).insert(any());
  }
}