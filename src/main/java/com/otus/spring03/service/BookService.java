package com.otus.spring03.service;

import com.otus.spring03.domain.Book;

public interface BookService {

    void insertBook(Book book);

    Book getBookById(long id);

    void updateBook(Book book);

    void deleteBookById(long id);

}
