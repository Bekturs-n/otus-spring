package com.otus.spring03.service;

import com.otus.spring03.model.Author;

public interface AuthorService {
  Author getAuthorBy(long id);

  Author getAuthorBy(String authorName);

  Long getCount();

  void update(Author author);

  Author save(Author author);

  void remove(Author author);

  void removeBy(long id);

  Author getOrCreateAuthor(String authorName);
}
