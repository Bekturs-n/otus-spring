package com.otus.spring03.service.impl;

import com.otus.spring03.dao.CommentDao;
import com.otus.spring03.model.Comment;
import com.otus.spring03.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Log4j2
@Component
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

  private final CommentDao commentDao;

  @Override
  public Comment save(Comment comment) {
    return commentDao.save(comment);
  }

  @Override
  public void remove(Comment comment) {
    commentDao.delete(comment);
  }

  @Override
  public void update(Comment comment) {
    Optional<Comment> optional = commentDao.findById(comment.getId());
    if (optional.isEmpty()) {
      log.error("No author with this credential");
      return;
    }

    Comment commentForUpdate = optional.get();
    copyField(comment, commentForUpdate);
    commentDao.save(commentForUpdate);
  }

  @Override
  public Comment findBy(long id) {
    Optional<Comment> comment = commentDao.findById(id);
    if (comment.isEmpty()) {
      log.info("No comment with this credentials");
    }
    return comment.get();
  }

  private void copyField(Comment from, Comment to) {
    if (from.getComment() != null) {
      to.setComment(from.getComment());
    }
    if (from.getBook() != null) {
      to.setBook(from.getBook());
    }
  }
}
