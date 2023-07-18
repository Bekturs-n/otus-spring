package com.otus.spring03.service.impl;

import com.otus.spring03.dao.AuthorDaoJdbc;
import com.otus.spring03.model.Author;
import com.otus.spring03.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Component
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

  private final DataSource dataSource;
  private final AuthorDaoJdbc authorDaoJdbc;

  @Override
  public Author getAuthorByName(String name) {
    return authorDaoJdbc.getByName(name);
  }

  @Override
  public Author getAuthorById(long id) {
    return authorDaoJdbc.getById(id);
  }

  @Override
  public Long getCount() {
    return authorDaoJdbc.count();
  }

  @Override
  public void update(Author author) {
    authorDaoJdbc.update(author);
  }

  @Override
  public void save(Author author) {
    authorDaoJdbc.insert(author);
  }

  @Override
  public void removeById(long id) {
    authorDaoJdbc.deleteById(id);
  }

  @Override
  @Transactional
  public Author getOrCreateAuthor(String authorName) {
    Author author = authorDaoJdbc.getByName(authorName);
    if (author == null) {
      author = Author.builder().author(authorName).surname("").build();
      author.setId(authorDaoJdbc.count() + 1);
      authorDaoJdbc.insert(author);
    }

    return author;
  }
}
