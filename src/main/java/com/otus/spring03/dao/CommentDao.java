package com.otus.spring03.dao;

import com.otus.spring03.model.Comment;

import java.util.List;

public interface CommentDao {
  Comment insert(Comment comment);

  void delete(Comment comment);

  void update(Comment comment);

  Comment findById(long id);

  List<Comment> findByBookId(long id);
}
