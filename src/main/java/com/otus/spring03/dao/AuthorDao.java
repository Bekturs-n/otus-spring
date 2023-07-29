package com.otus.spring03.dao;

import com.otus.spring03.model.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorDao extends CrudRepository<Author, Long> {

  @Override
  List<Author> findAll();

  Optional<Author> findByAuthor(String authorName);

  Author findFirstByOrderByIdDesc();

}
