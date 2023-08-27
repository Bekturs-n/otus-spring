package com.otus.spring03.service.impl;

import com.otus.spring03.dao.BookDao;
import com.otus.spring03.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

  @Mock
  private BookDao bookDaoJdbc;
  @InjectMocks
  private BookServiceImpl bookService;

  private Book book;

  @BeforeEach
  void beforeEach() {
    book = Book.builder().bookName("Name").id(1L).build();
  }

  @Test
  void getAll() {
    when(bookDaoJdbc.findAll()).thenReturn(Collections.singletonList(book));
    assertEquals(Collections.singletonList(book), bookService.getAll());
  }

  @Test
  void getBookById() {
    when(bookDaoJdbc.findById(1L)).thenReturn(Optional.of(book));
    assertEquals(book, bookService.getBy(1));
  }

  @Test
  void getBookByName() {
    when(bookDaoJdbc.findByBookName("Book")).thenReturn(Optional.of(book));
    assertEquals(book, bookService.getByName("Book"));
  }

}