package com.otus.spring03.dao;

import com.otus.spring03.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {

  Author insert(Author author);

  Optional<Author> findById(long id);

  Optional<Author> findByName(String id);

  void delete(Author author);

  void update(Author author);

  List<Author> findAll();
}
