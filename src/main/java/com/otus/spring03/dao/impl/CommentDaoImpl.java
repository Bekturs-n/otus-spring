package com.otus.spring03.dao.impl;

import com.otus.spring03.dao.CommentDao;
import com.otus.spring03.model.Comment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDaoImpl implements CommentDao {

  @PersistenceContext
  private EntityManager em;

  @Override
  public Comment insert(Comment comment) {
    if (comment.getId() == 0) {
      em.persist(comment);
      return comment;
    } else {
      return em.merge(comment);
    }
  }

  @Override
  public void delete(Comment comment) {
    em.remove(comment);
  }

  @Override
  public void update(Comment comment) {
    em.merge(comment);
  }

  @Override
  public Comment findById(long id) {
    return em.find(Comment.class, id);
  }
}
