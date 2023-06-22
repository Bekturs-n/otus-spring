package com.otus.spring03.service.impl;

import com.otus.spring03.dao.AuthorDao;
import com.otus.spring03.dao.BookDao;
import com.otus.spring03.dao.GenreDao;
import com.otus.spring03.domain.Author;
import com.otus.spring03.domain.Book;
import com.otus.spring03.domain.Genre;
import com.otus.spring03.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    @Override
    public void insertBook(Book book) {
        checkAuthorAndGenre(book.getAuthor(), book.getGenre());
        bookDao.insert(book);
    }

    @Override
    public Book getBookById(long id) {
        return bookDao.getById(id);
    }

    @Override
    public void updateBook(Book book) {
        checkAuthorAndGenre(book.getAuthor(), book.getGenre());
        bookDao.update(book);
    }

    @Override
    public void deleteBookById(long id) {
        bookDao.deleteById(id);
    }

    private void checkAuthorAndGenre(Author author, Genre genre) {
        if (authorDao.getByName(author.getAuthor()) == null) {
            author.setId(authorDao.count() + 1);
            authorDao.insert(author);
        }
        if (genreDao.getById(genre.getId()) == null) {
            genre.setId(genreDao.count() + 1);
            genreDao.insert(genre);
        }
    }
}
