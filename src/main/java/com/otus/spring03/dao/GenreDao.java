package com.otus.spring03.dao;

import com.otus.spring03.domain.Genre;

public interface GenreDao {

    long count();

    void insert(Genre genre);

    void update(Genre genre);

    void deleteById(long id);

    Genre getById(long id);

    Genre getByName(String genreName);
}
