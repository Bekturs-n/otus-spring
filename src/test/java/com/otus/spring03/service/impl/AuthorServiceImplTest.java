package com.otus.spring03.service.impl;

import com.otus.spring03.dao.AuthorDao;
import com.otus.spring03.model.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class AuthorServiceImplTest {

  @InjectMocks
  private AuthorServiceImpl authorService;

  @Mock
  private AuthorDao authorDao;

  @Test
  void getAuthorById() {
    Author author = new Author();
    author.setAuthor("Author");
    author.setSurname("AuthorSurname");

    when(authorDao.findById(anyLong())).thenReturn(Optional.of(author));
    assertEquals(author, authorService.getAuthorBy(1));
  }

  @Test
  void getOrCreateAuthor() {
    String authorName = "AuthorName";
    Author author = new Author();
    author.setAuthor("Author");

    when(authorDao.findByAuthor(anyString())).thenReturn(Optional.empty());
    when(authorDao.save(any())).thenReturn(author);
    assertEquals(author, authorService.getOrCreateAuthor(authorName));
  }
}