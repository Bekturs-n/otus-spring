package com.otus.spring03.shell;

import com.otus.spring03.model.Book;
import com.otus.spring03.service.AuthorService;
import com.otus.spring03.service.BookService;
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

  private String login;
  private String pass;

  @ShellMethod(value = "SingIn ", key = { "i", "in" })
  public void signIn(String login, String pass) {
    this.login = login;
    this.pass = pass;
  }

  @ShellMethod(value = "Add book", key = { "a", "add" })
  public String addNewBook(String bookName, String autor, String genre) {
    return bookService.checkAndSaveBook(bookName, autor, genre);
  }

  @ShellMethod(value = "Update book", key = { "u", "update" })
  public void updateBook(Integer bookId, String newBookName, String newAutorName, String newAuthorSurname,
      String newGenreName) {
    bookService.updateBook(bookId, newBookName, newAutorName, newAuthorSurname, newGenreName);
  }

  @ShellMethod(value = "Get book by name", key = { "gn", "getByName" })
  public String getBookByName(String bookName) {
    Book book = bookService.getBookBy(bookName);
    if (book == null) {
      return "No books with your conditions";
    }

    return book.toString();
  }

  @ShellMethod(value = "Get book by id", key = { "g", "getId" })
  public String getBookById(Long bookId) {
    Book book = bookService.getBookBy(bookId);
    if (book == null) {
      return "No books with your conditions";
    }
    return book.toString();
  }

  @ShellMethod(value = "Delete by id", key = { "d", "delete" })
  public void removeBook(Long bookId) {
    bookService.deleteBookById(bookId);
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
