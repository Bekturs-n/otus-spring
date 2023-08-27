package com.otus.spring03.shell;

import com.otus.spring03.model.Book;
import com.otus.spring03.service.AuthorService;
import com.otus.spring03.service.BookService;
import com.otus.spring03.service.GenreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@Slf4j
@ShellComponent
@RequiredArgsConstructor
public class ApplicationShellComponent {

  private final BookService bookService;
  private final AuthorService authorService;
  private final GenreService genreService;

  private String login;
  private String pass;

  // пока не нужен
  @ShellMethod(value = "SingIn ", key = { "i", "in" })
  public void signIn(String login, String pass) {
    this.login = login;
    this.pass = pass;
  }

  @ShellMethod(value = "Add book", key = { "a", "add" })
  public String addNewBook(String bookName, String authorName, String comment, String[] genreNames) {
    authorService.getOrCreateAuthor("Alexandre");
    return bookService.checkAndSave(bookName, authorName, comment, genreNames);
  }

  @ShellMethod(value = "Update book", key = { "u", "update" })
  public void updateBook(Integer bookId, String newBookName) {
    bookService.updateBook(bookId, newBookName);
  }

  @ShellMethod(value = "Get book by name", key = { "gn", "getByName" })
  public Book getBookByName(String bookName) {
    Book book = bookService.getByName(bookName);
    return book;
  }

  @ShellMethod(value = "Get book by id", key = { "g", "getId" })
  public String getBookById(Long bookId) {
    Book book = bookService.getBy(bookId);
    return book.toString();
  }

  @ShellMethod(value = "Delete by id", key = { "d", "delete" })
  public void removeBook(Long bookId) {
    bookService.removeBy(bookId);
  }

  @ShellMethod(value = "Get All", key = { "ga", "Get all" })
  public List<Book> getAll() {
    return bookService.getAll();
  }

  private Availability isUserNameNotNull() {
    // пока не надо
    return login == null ? Availability.unavailable("Before LogIn in system") : Availability.available();
  }

}
