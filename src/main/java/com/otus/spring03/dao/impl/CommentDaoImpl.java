package com.otus.spring03.dao.impl;

import com.otus.spring03.dao.CommentDao;
import com.otus.spring03.model.Book;
import com.otus.spring03.model.Comment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
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

  @Override
  public List<Comment> findByBookId(long id) {
    return em.createQuery("SELECT c FROM Comment AS c JOIN FETCH c.book WHERE c.book.id=:id", Comment.class)
            .setParameter("id", id).getResultList();
  }
}
