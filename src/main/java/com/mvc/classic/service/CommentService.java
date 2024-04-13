package com.mvc.classic.service;

import com.mvc.classic.domain.Comment;

public interface CommentService {
  Comment save(Comment comment);

  void remove(Comment comment);

  void update(Comment comment);

  Comment findBy(long id);
}
