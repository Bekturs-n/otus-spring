package com.mvc.classic.repository;

import com.mvc.classic.domain.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookDao extends CrudRepository<Book, Long> {

  @Override
  List<Book> findAll();

  Optional<Book> findByBookName(String name);

}
