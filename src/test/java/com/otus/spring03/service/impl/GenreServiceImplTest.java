package com.otus.spring03.service.impl;

import com.otus.spring03.dao.GenreDao;
import com.otus.spring03.model.Genre;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GenreServiceImplTest {

  @Mock
  private GenreDao genreDao;

  @InjectMocks
  private GenreServiceImpl genreService;

  @Test
  void getById() {
    Genre genre = new Genre();
    when(genreDao.findById(1L)).thenReturn(Optional.of(genre));

    assertEquals(genre, genreService.getBy(1));
  }

  @Test
  void getByName() {
    Genre genre = new Genre();
    when(genreDao.findByGenre(anyString())).thenReturn(Optional.of(genre));

    assertEquals(genre, genreService.getBy(anyString()));
  }

}