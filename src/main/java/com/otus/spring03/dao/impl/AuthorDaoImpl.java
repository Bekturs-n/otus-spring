package com.otus.spring03.dao.impl;

import com.otus.spring03.dao.AuthorDao;
import com.otus.spring03.model.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AuthorDaoImpl implements AuthorDao {

  @PersistenceContext
  private EntityManager em;

  @Override
  public Author insert(Author author) {
    if (author.getId() == 0) {
      em.persist(author);
      return author;
    } else {
      return em.merge(author);
    }
  }

  @Override
  public Optional<Author> findById(long id) {
    return Optional.ofNullable(em.find(Author.class, id));
  }

  @Override
  public Optional<Author> findByName(String author) {
    TypedQuery<Author> tq = em.createQuery("SELECT a FROM Author a WHERE a.author = :author", Author.class);
    tq.setParameter("author", author);
    return Optional.ofNullable(tq.getSingleResult());
  }

  @Override
  public void delete(Author author) {
    em.remove(author);
  }

  @Override
  public void update(Author author) {
    em.merge(author);
  }

  @Override
  public List<Author> findAll() {
    TypedQuery<Author> tq = em.createQuery("SELECT a FROM Author a", Author.class);
    return tq.getResultList();
  }
}
