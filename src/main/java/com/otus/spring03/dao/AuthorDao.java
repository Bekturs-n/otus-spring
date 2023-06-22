package com.otus.spring03.dao;

import com.otus.spring03.domain.Author;

public interface AuthorDao {

    long count();

    void insert(Author author);

    void update(Author author);

    void deleteById(long id);

    Author getById(long id);

    Author getByName(String authorName);
}
