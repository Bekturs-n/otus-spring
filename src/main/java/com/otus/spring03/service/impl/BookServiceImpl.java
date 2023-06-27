package com.otus.spring03.service.impl;

import com.otus.spring03.dao.BookDao;
import com.otus.spring03.model.Author;
import com.otus.spring03.model.Book;
import com.otus.spring03.model.Genre;
import com.otus.spring03.service.AuthorService;
import com.otus.spring03.service.BookService;
import com.otus.spring03.service.GenreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

  private final BookDao bookDao;
  private final AuthorService authorService;
  private final GenreService genreService;

  @Override
  public void saveNewBook(Book book) {
    bookDao.insert(book);
  }

  @Override
  public Book getBookById(long id) {
    return bookDao.getById(id);
  }

  @Override
  public Book getBookByName(String name) {
    return bookDao.getByName(name);
  }

  @Override
  public void updateBook(Integer bookId, String newBookName, String newAutorName, String newAuthorSurname,
      String newGenreName) {
    Book book = bookDao.getById(bookId);
    if (book == null) {
      return;
    }

    book.setBookName(newBookName);

    Author author = book.getAuthor();
    author.setAuthor(newAutorName);
    author.setSurname(newAuthorSurname);

    Genre genre = book.getGenre();
    genre.setGenre(newGenreName);

    genreService.update(genre);
    authorService.update(author);
    bookDao.update(book);
  }

  @Override
  public void deleteBookById(long id) {
    Book book = bookDao.getById(id);
    if (book == null) {
      return;
    }
    bookDao.deleteById(id);
  }

  @Override
  public String checkAndSaveBook(String bookName, String autor, String genreName) {
    Book book = getBookByName(bookName);

    if (book != null) {
      return "This book we have in DB";
    }
    Author author = authorService.getOrCreateAuthor(autor);
    Genre genre = genreService.getOrCreateGenre(genreName);

    book = Book.builder().bookName(bookName).author(author).genre(genre).build();
    bookDao.insert(book);

    return "New book saved ";
  }
}
