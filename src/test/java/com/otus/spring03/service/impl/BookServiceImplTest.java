package com.otus.spring03.service.impl;

import com.otus.spring03.dao.BookDao;
import com.otus.spring03.model.Book;
import com.otus.spring03.service.AuthorService;
import com.otus.spring03.service.CommentService;
import com.otus.spring03.service.GenreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

  @Mock
  private AuthorService authorService;
  @Mock
  private GenreService genreService;
  @Mock
  private BookDao bookDaoJdbc;
  @Mock
  private CommentService commentService;

  private BookServiceImpl bookService;
  private Book book;

  @BeforeEach
  void beforeEach() {
    bookService = new BookServiceImpl(bookDaoJdbc, authorService, genreService, commentService);
    book = Book.builder().id(1L).build();
  }

  @Test
  void saveNewBook() {
    when(bookDaoJdbc.insert(any())).thenReturn(any());
    bookService.save(book);
    verify(bookDaoJdbc).insert(any());
  }

  @Test
  void getAll() {
    when(bookDaoJdbc.findAll()).thenReturn(Collections.singletonList(book));
    assertEquals(Collections.singletonList(book), bookService.getAll());
  }

  @Test
  void getBookById() {
    when(bookDaoJdbc.findById(1)).thenReturn(Optional.of(book));
    assertEquals(book, bookService.getBy(1));
  }

  @Test
  void getBookByName() {
    when(bookDaoJdbc.findByName("Book")).thenReturn(book);
    assertEquals(book, bookService.getByName("Book"));
  }

  @Test
  void deleteBookById() {
    when(bookDaoJdbc.findById(anyLong())).thenReturn(Optional.of(book));
    doNothing().when(bookDaoJdbc).delete(book);
    bookService.removeBy(book.getId());
    verify(bookDaoJdbc).delete(book);
  }
}