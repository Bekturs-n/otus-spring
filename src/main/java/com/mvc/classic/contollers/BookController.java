package com.mvc.classic.contollers;

import com.mvc.classic.dto.BookDto;
import com.mvc.classic.exceptions.NotFoundException;
import com.mvc.classic.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public String listBook(Model model) {
        var books = bookService.getAll()
                .stream()
                .map(BookDto::fromDomainObject)
                .collect(Collectors.toList());

        model.addAttribute("books", books);
        return "books";
    }

    @PostMapping("/edit")
    public String listBook(@ModelAttribute("book") BookDto book) {
        bookService.updateBook(book.getId(), book.getName());
        return "redirect:/books";
    }

    @GetMapping("/edit")
    public String listBook(@RequestParam("id") long id, Model model) {
        var book = bookService.getBy(id)
                .map(BookDto::fromDomainObject)
                .orElseThrow(NotFoundException::new);

        model.addAttribute("book", book);
        return "edit";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") long id, Model model) {
        bookService.removeBy(id);

        var book = bookService.getAll()
                .stream()
                .map(BookDto::fromDomainObject)
                .collect(Collectors.toList());

        model.addAttribute("book", book);
        return "redirect:/books";
    }

    @PostMapping("/books/add")
    public String add(@ModelAttribute("book") BookDto book, Model model) {
        bookService.checkAndSave(book.getName(), book.getAuthor(),"", new String[]{book.getGenre()});

        model.addAttribute("book", book);
        return "redirect:/books";
    }

    @GetMapping("/books/add")
    public String add() {
        return "addBook";
    }

}
