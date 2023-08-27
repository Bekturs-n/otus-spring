package com.otus.spring03.dao.impl;

import com.otus.spring03.dao.GenreDao;
import com.otus.spring03.model.Genre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Component
public class GenreDaoImpl implements GenreDao {

  @PersistenceContext
  private EntityManager em;

  @Override
  public Genre insert(Genre genre) {
    if (genre.getId() == 0) {
      em.persist(genre);
      return genre;
    } else {
      return em.merge(genre);
    }
  }

  @Override
  public Optional<Genre> findById(long id) {
    return Optional.ofNullable(em.find(Genre.class, id));
  }

  @Override
  public void update(Genre genre) {
    em.merge(genre);
  }

  @Override
  public void delete(Genre genre) {
    em.remove(genre);
  }

  @Override
  public List<Genre> findAll() {
    TypedQuery<Genre> tq = em.createQuery("SELECT g FROM Genre g", Genre.class);
    return tq.getResultList();
  }

  @Override
  public Genre findByName(String genre) {
    TypedQuery<Genre> tq = em.createQuery("SELECT g FROM Genre g WHERE g.genre = :genre", Genre.class);
    tq.setParameter("genre", genre);
    return tq.getSingleResult();
  }
}
