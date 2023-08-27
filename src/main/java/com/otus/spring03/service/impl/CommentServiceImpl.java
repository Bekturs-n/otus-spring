package com.otus.spring03.service.impl;

import com.otus.spring03.dao.CommentDao;
import com.otus.spring03.model.Book;
import com.otus.spring03.model.Comment;
import com.otus.spring03.service.BookService;
import com.otus.spring03.service.CommentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j2
@Component
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

  private final CommentDao commentDao;

  @Override
  @Transactional
  public Comment save(Comment comment) {
    return commentDao.insert(comment);
  }

  @Override
  @Transactional
  public void remove(Comment comment) {
    commentDao.delete(comment);
  }

  @Override
  @Transactional
  public void update(Comment comment) {
    commentDao.update(comment);
  }

  @Override
  public Comment findBy(long id) {
    Comment comment = commentDao.findById(id);
    if (comment == null) {
      log.info("No comment with this credentials");
    }
    return comment;
  }
  @Override
  @Transactional
  public List<Comment> getCommentsByBookId(long bookId) {
    return commentDao.findByBookId(bookId);
  }

}
