package com.otus.spring03.service.impl;

import com.otus.spring03.dao.BookDaoJdbc;
import com.otus.spring03.model.Author;
import com.otus.spring03.model.Book;
import com.otus.spring03.model.Genre;
import com.otus.spring03.service.AuthorService;
import com.otus.spring03.service.GenreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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
  private BookDaoJdbc bookDaoJdbc;

  private BookServiceImpl bookService;
  private Book book;

  @BeforeEach
  void beforeEach() {
    bookService = new BookServiceImpl(bookDaoJdbc, authorService, genreService);
    book = new Book(1, "Book", Author.builder().id(1).build(), Genre.builder().id(1).build());
  }

  @Test
  void saveNewBook() {
    doNothing().when(bookDaoJdbc).insert(any());
    bookService.saveNewBook(book);
    verify(bookDaoJdbc).insert(any());
  }

  @Test
  void getAll() {
    when(bookDaoJdbc.getAll()).thenReturn(Collections.singletonList(book));
    assertEquals(Collections.singletonList(book), bookService.getAll());
  }

  @Test
  void getBookById() {
    when(bookDaoJdbc.getById(1)).thenReturn(book);
    assertEquals(book, bookService.getBookById(1));
  }

  @Test
  void getBookByName() {
    when(bookDaoJdbc.getByName("Book")).thenReturn(book);
    assertEquals(book, bookService.getBookByName("Book"));
  }

  @Test
  void deleteBookById() {
    when(bookDaoJdbc.getById(book.getId())).thenReturn(book);
    doNothing().when(bookDaoJdbc).deleteById(book.getId());
    bookService.deleteBookById(book.getId());
    verify(bookDaoJdbc).deleteById(book.getId());
  }

  @Test
  void checkAndSaveBook() {
    when(bookDaoJdbc.getByName(book.getBookName())).thenReturn(book);
    String str = bookService.checkAndSaveBook(book.getBookName(), book.getAuthor().getAuthor(), book.getGenre().getGenre());

    verify(bookDaoJdbc).getByName(book.getBookName());
    assertEquals("This book we have in DB", str);
  }
}