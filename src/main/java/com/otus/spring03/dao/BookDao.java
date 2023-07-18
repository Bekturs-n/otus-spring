package com.otus.spring03.dao;

import com.otus.spring03.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookDao extends CrudRepository<Book, Long> {

  @Override
  List<Book> findAll();

  Optional<Book> findByBookName(String name);

}
