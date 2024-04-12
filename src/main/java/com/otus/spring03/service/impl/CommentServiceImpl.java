package com.otus.spring03.service.impl;

import com.otus.spring03.dao.BookDao;
import com.otus.spring03.dao.CommentDao;
import com.otus.spring03.model.Book;
import com.otus.spring03.model.Comment;
import com.otus.spring03.service.CommentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;

import java.util.List;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Log4j2
@Component
@RequiredArgsConstructor
@EnableTransactionManagement
public class CommentServiceImpl implements CommentService {

  private final CommentDao commentDao;
  private final BookDao bookDao;

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
    Book book = bookDao.findById(bookId).orElse(null);

    if (book == null) {
      return null;
    }

    // force initialize of a proxy comments
    Hibernate.initialize(book.getComments());
    return book.getComments();
  }
}
