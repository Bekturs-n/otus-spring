package com.otus.spring03.service;

import com.otus.spring03.model.Author;

public interface AuthorService {

  Author getAuthorByName(String name);

  Author getAuthorById(long id);

  Long getCount();

  void save(Author author);

  Author getOrCreateAuthor(String author);

  void update(Author author);

  void removeById(long id);
}
