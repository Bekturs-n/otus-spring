package com.mvc.classic.repository;

import com.mvc.classic.domain.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorDao extends CrudRepository<Author, Long> {

  @Override
  List<Author> findAll();

  Optional<Author> findByAuthor(String authorName);

}
