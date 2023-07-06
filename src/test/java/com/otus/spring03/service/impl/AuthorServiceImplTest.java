package com.otus.spring03.service.impl;

import com.otus.spring03.dao.AuthorDaoJdbc;
import com.otus.spring03.model.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {

  private AuthorServiceImpl authorService;

  @Mock
  private AuthorDaoJdbc authorDaoJdbc;

  @BeforeEach
  public void beforeEach(){
    authorService = new AuthorServiceImpl(authorDaoJdbc);
  }

  @Test
  void getAuthorById() {
    Author author = Author.builder().author("Author").surname("AuthorSurname").build();
    when(authorDaoJdbc.getById(anyLong())).thenReturn(author);
    assertEquals(author, authorService.getAuthorById(1));
  }

  @Test
  void getOrCreateAuthor() {
    String authorName = "AuthorName";
    Author author = Author.builder().id(1).author(authorName).surname("").build();

    when(authorDaoJdbc.getByName(anyString())).thenReturn(null);
    assertEquals(author, authorService.getOrCreateAuthor(authorName));
  }
}