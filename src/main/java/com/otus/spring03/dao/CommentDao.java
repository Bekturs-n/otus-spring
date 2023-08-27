package com.otus.spring03.dao;

import com.otus.spring03.model.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentDao extends CrudRepository<Comment, Long> {

}
