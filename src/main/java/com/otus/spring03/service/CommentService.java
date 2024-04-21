package com.otus.spring03.service;

import com.otus.spring03.model.Comment;

import java.util.List;

public interface CommentService {
    Comment save(Comment comment);

    void remove(Comment comment);

    void update(Comment comment);

    Comment findBy(long id);

    List<Comment> getCommentsByBookId(long bookId);
}
