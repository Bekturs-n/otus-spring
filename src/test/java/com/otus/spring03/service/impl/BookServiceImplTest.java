package com.otus.spring03.service.impl;

import com.otus.spring03.dao.BookDao;
import com.otus.spring03.model.Book;
import com.otus.spring03.service.AuthorService;
import com.otus.spring03.service.CommentService;
import com.otus.spring03.service.GenreService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
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
    private BookDao bookDao;
    @Mock
    private CommentService commentService;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void saveNewBook() {
        when(bookDao.save(any())).thenReturn(any());
        bookService.save(createBook());
        verify(bookDao).save(any());
    }

    @Test
    void getAll() {
        Book book = createBook();
        when(bookDao.findAll()).thenReturn(Collections.singletonList(book));
        assertEquals(Collections.singletonList(book), bookService.getAll());
    }

    @Test
    void getBookById() {
        Book book = createBook();
        when(bookDao.findById(1l)).thenReturn(Optional.of(book));
        assertEquals(book, bookService.getBy(1));
    }

    @Test
    void getBookByName() {
        Book book = createBook();
        when(bookDao.findByBookName("Book")).thenReturn(Optional.of(book));
        assertEquals(book, bookService.getByName("Book"));
    }

    @Test
    void deleteBookById() {
        Book book = createBook();
        doNothing().when(bookDao).deleteById(book.getId());
        bookService.removeBy(book.getId());
        verify(bookDao).deleteById(book.getId());
    }

    private Book createBook() {
        return Book.builder().id(1L).build();
    }
}