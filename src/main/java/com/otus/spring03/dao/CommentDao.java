package com.otus.spring03.dao;

import com.otus.spring03.model.Comment;

public interface CommentDao {
  Comment insert(Comment comment);

  void delete(Comment comment);

  void update(Comment comment);

  Comment findById(long id);
}
