package com.mvc.classic.service;

import com.mvc.classic.domain.Author;

public interface AuthorService {
  Author getAuthorBy(long id);

  Long getCount();

  void update(Author author);

  Author save(Author author);

  void remove(Author author);

  void removeBy(long id);

  Author getOrCreateAuthor(String authorName);
}
