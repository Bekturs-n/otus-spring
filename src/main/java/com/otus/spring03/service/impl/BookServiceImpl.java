package com.otus.spring03.service.impl;

import com.otus.spring03.dao.BookDaoJdbc;
import com.otus.spring03.model.Author;
import com.otus.spring03.model.Book;
import com.otus.spring03.model.Genre;
import com.otus.spring03.service.AuthorService;
import com.otus.spring03.service.BookService;
import com.otus.spring03.service.GenreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

  private final BookDaoJdbc bookDaoJdbc;
  private final AuthorService authorService;
  private final GenreService genreService;

  @Override
  public void saveNewBook(Book book) {
    bookDaoJdbc.insert(book);
  }

  @Override
  public List<Book> getAll() {
    List<Book> list = bookDaoJdbc.getAll();
    if (list == null) {
      log.error("DataBase is empty ");
      return null;
    }
    return list;
  }

  @Override
  public Book getBookBy(long id) {
    return bookDaoJdbc.getById(id);
  }

  @Override
  public Book getBookBy(String name) {
    return bookDaoJdbc.getByName(name);
  }

  @Override
  public void updateBook(Integer bookId, String newBookName, String newAutorName, String newAuthorSurname,
      String newGenreName) {
    Book book = bookDaoJdbc.getById(bookId);
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
    bookDaoJdbc.update(book);
  }

  @Override
  public void deleteBookById(long id) {
    Book book = bookDaoJdbc.getById(id);
    if (book == null) {
      return;
    }
    bookDaoJdbc.deleteById(id);
  }

  @Override
  public String checkAndSaveBook(String bookName, String autor, String genreName) {
    Book book = bookDaoJdbc.getByName(bookName);

    if (book != null) {
      return "This book we have in DB";
    }
    Author author = authorService.getOrCreateAuthor(autor);
    Genre genre = genreService.getOrCreateGenre(genreName);

    book = Book.builder().bookName(bookName).author(author).genre(genre).build();
    bookDaoJdbc.insert(book);

    return "New book saved ";
  }
}
