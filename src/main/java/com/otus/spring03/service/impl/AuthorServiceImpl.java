package com.otus.spring03.service.impl;

import com.otus.spring03.dao.AuthorDao;
import com.otus.spring03.model.Author;
import com.otus.spring03.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

  private final AuthorDao authorDao;

  @Override
  public Author getAuthorByName(String name) {
    return authorDao.getByName(name);
  }

  @Override
  public Long getCount() {
    return authorDao.count();
  }

  @Override
  public void update(Author author) {
    authorDao.update(author);
  }

  @Override
  public void save(Author author) {
    authorDao.insert(author);
  }

  @Override
  public void removeById(long id) {
    authorDao.deleteById(id);
  }

  @Override
  public Author getOrCreateAuthor(String authorName) {
    Author author = authorDao.getByName(authorName);
    if (author == null) {
      author = Author.builder().author(authorName).surname("").build();
      author.setId(authorDao.count() + 1);
      authorDao.insert(author);
    }

    return author;
  }
}
