package com.otus.spring03.service.impl;

import com.otus.spring03.dao.AuthorDao;
import com.otus.spring03.model.Author;
import com.otus.spring03.service.AuthorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Log4j2
@Component
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

  private final AuthorDao authorDaoJdbc;

  @Override
  @Transactional
  public Author getAuthorBy(long id) {
    Optional<Author> author = authorDaoJdbc.findById(id);

    if (author.isPresent()) {
      return author.get();
    } else {
      log.info("No author with this credentials");
      return null;
    }
  }

  @Override
  @Transactional
  public Long getCount() {
    return (long) authorDaoJdbc.findAll().size();
  }

  @Override
  @Transactional
  public void update(Author author) {
    Optional<Author> optionalAuthor = authorDaoJdbc.findById(author.getId());
    if (optionalAuthor.isEmpty()) {
      log.error("No author with this credential");
      return;
    }

    Author authorForUpdate = optionalAuthor.get();
    copyField(author, authorForUpdate);
    authorDaoJdbc.save(authorForUpdate);
  }

  @Override
  @Transactional
  public Author save(Author author) {
    return authorDaoJdbc.save(author);
  }

  @Transactional
  @Override
  public void remove(Author author) {
    authorDaoJdbc.delete(author);
  }

  @Override
  @Transactional
  public void removeBy(long id) {
    Author author = getAuthorBy(id);
    if (author != null) {
      authorDaoJdbc.delete(author);
    }
  }

  @Override
  @Transactional
  public Author getOrCreateAuthor(String authorName) {
    Optional<Author> optionalAuthor = authorDaoJdbc.findByAuthor(authorName);
    return optionalAuthor.orElseGet(() -> authorDaoJdbc.save(Author.builder().author(authorName).build()));
  }

  private void copyField(Author from, Author to) {
    if (from.getSurname() != null) {
      to.setSurname(from.getSurname());
    }
    if (from.getAuthor() != null) {
      to.setAuthor(from.getAuthor());
    }
    if (from.getBooks() != null && !from.getBooks().isEmpty()) {
      to.setBooks(from.getBooks());
    }
  }

}
