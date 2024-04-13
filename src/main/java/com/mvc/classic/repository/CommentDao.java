package com.mvc.classic.repository;

import com.mvc.classic.domain.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentDao extends CrudRepository<Comment, Long> {

}
