package com.otus.spring03.service.impl;

import com.otus.spring03.dao.impl.GenreDaoImpl;
import com.otus.spring03.model.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GenreServiceImplTest {

  @Mock
  private GenreDaoImpl genreDaoJdbcImpl;

  private GenreServiceImpl genreService;

  @BeforeEach
  public void beforeEach(){
    genreService = new GenreServiceImpl(genreDaoJdbcImpl);
  }

  @Test
  void getById() {
    Genre genre = new Genre();
    when(genreDaoJdbcImpl.findById(1)).thenReturn(Optional.of(genre));

    assertEquals(genre, genreService.getBy(1));
    verify(genreDaoJdbcImpl).findById(1);
  }

  @Test
  void getByName() {
    Genre genre = new Genre();
    when(genreDaoJdbcImpl.findByName(anyString())).thenReturn(genre);

    assertEquals(genre, genreService.getBy(anyString()));
    verify(genreDaoJdbcImpl).findByName(anyString());
  }

  @Test
  void update() {
    doNothing().when(genreDaoJdbcImpl).update(any());
    genreService.update(any());
    verify(genreDaoJdbcImpl).update(any());
  }

  @Test
  void removeById() {
    Genre genre = Genre.builder().id(1L).build();

    when(genreDaoJdbcImpl.findById(anyLong())).thenReturn(Optional.of(genre));
    doNothing().when(genreDaoJdbcImpl).delete(any());
    genreService.removeBy(1L);
    verify(genreDaoJdbcImpl).delete(any());
  }

}