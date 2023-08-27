package com.otus.spring03.service.impl;

import com.otus.spring03.dao.GenreDao;
import com.otus.spring03.model.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
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
        verify(genreDao).findById(1L);
    }

    @Test
    void getByName() {
        Genre genre = new Genre();
        when(genreDao.findByGenre(anyString())).thenReturn(Optional.of(genre));

        assertEquals(genre, genreService.getBy(anyString()));
        verify(genreDao).findByGenre(anyString());
    }

    @Test
    void update() {
        Genre genre = new Genre();

        when(genreDao.findById(anyLong())).thenReturn(Optional.of(genre));
        when(genreDao.save(any())).thenReturn(genre);

        genreService.update(genre);
        verify(genreDao).findById(anyLong());
        verify(genreDao).save(genre);
    }

    @Test
    void removeById() {
        doNothing().when(genreDao).deleteById(anyLong());

        genreService.removeBy(anyLong());
        verify(genreDao).deleteById(anyLong());
    }

}