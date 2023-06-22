package com.otus.spring03.dao;

import com.otus.spring03.domain.Book;

public interface BookDao {

    void insert(Book book);

    void update(Book book);

    void deleteById(long id);

    Book getById(long id);

}
