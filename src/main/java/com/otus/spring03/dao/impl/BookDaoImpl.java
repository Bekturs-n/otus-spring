package com.otus.spring03.dao.impl;

import com.otus.spring03.dao.BookDao;
import com.otus.spring03.model.Book;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Component
public class BookDaoImpl implements BookDao {

  @PersistenceContext
  private EntityManager em;

  @Override
  public Book insert(Book book) {
    if (book.getId() == 0) {
      em.persist(book);
      return book;
    } else {
      return em.merge(book);
    }
  }

  @Override
  public Optional<Book> findById(long id) {
    EntityGraph<?> eg = em.getEntityGraph("book-genre-eg");
    TypedQuery<Book> tq = em.createQuery("SELECT b FROM Book b WHERE b.id = :id", Book.class);
    tq.setParameter("id", id);
    tq.setHint("jakarta.persistence.fetchgraph", eg);
    return Optional.ofNullable(tq.getSingleResult());
  }

  @Override
  public Book findByName(String name) {
    Book book;
    try {
      EntityGraph<?> eg = em.getEntityGraph("book-genre-eg");
      TypedQuery<Book> tq = em.createQuery("SELECT b FROM Book b WHERE b.bookName = :name", Book.class);
      tq.setParameter("name", name);
      tq.setHint("jakarta.persistence.fetchgraph", eg);
      book = tq.getSingleResult();
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
    return book;
  }

  @Override
  public Book update(Book book) {
    return em.merge(book);
  }

  @Override
  public void delete(Book book) {
    em.remove(book);
  }

  @Override
  public List<Book> findAll() {
    EntityGraph<?> eg = em.getEntityGraph("book-genre-eg");
    TypedQuery<Book> tq = em.createQuery("SELECT b FROM Book b", Book.class);
    tq.setHint("jakarta.persistence.fetchgraph", eg);
    return tq.getResultList();
  }

}
