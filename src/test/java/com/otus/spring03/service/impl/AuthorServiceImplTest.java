package com.otus.spring03.service.impl;

import com.otus.spring03.dao.AuthorDao;
import com.otus.spring03.model.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {

  private AuthorServiceImpl authorService;

  @Mock
  private AuthorDao authorDaoJdbc;

  @BeforeEach
  public void beforeEach(){
    authorService = new AuthorServiceImpl(authorDaoJdbc);
  }

  @Test
  void getAuthorById() {
    Author author = new Author();
    author.setAuthor("Author");
    author.setSurname("AuthorSurname");

    when(authorDaoJdbc.findById(anyLong())).thenReturn(Optional.of(author));
    assertEquals(author, authorService.getAuthorBy(1));
  }

  @Test
  void getOrCreateAuthor() {
    String authorName = "AuthorName";
    Author author = new Author();
    author.setAuthor("Author");

    when(authorDaoJdbc.findByName(anyString())).thenReturn(Optional.empty());
    when(authorDaoJdbc.insert(any())).thenReturn(author);
    assertEquals(author, authorService.getOrCreateAuthor(authorName));
  }
}