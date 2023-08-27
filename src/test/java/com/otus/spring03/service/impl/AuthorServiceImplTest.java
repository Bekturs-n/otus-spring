package com.otus.spring03.service.impl;

import com.otus.spring03.dao.AuthorDao;
import com.otus.spring03.model.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {

    @Mock
    private AuthorDao authorDao;
    @InjectMocks
    private AuthorServiceImpl authorService;

    private static final String NAME = "Name";
    private static final String SURNAME = "Surname";

    @Test
    void getAuthorById() {
        when(authorDao.findById(any())).thenReturn(Optional.of(createAuthor()));

        Author author = authorService.getAuthorBy(1);
        assertEquals(1, author.getId());
    }

    @Test
    void getOrCreateAuthor() {
        Author author = createAuthor();

        when(authorDao.findByAuthor(anyString())).thenReturn(Optional.empty());
        when(authorDao.save(any())).thenReturn(author);

        assertEquals(author, authorService.getOrCreateAuthor(NAME));
    }

    private Author createAuthor() {
        return Author.builder().id(1).author(NAME).surname(SURNAME).build();
    }
}