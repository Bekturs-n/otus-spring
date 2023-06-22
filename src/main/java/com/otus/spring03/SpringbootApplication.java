package com.otus.spring03;

import com.otus.spring03.domain.Author;
import com.otus.spring03.domain.Book;
import com.otus.spring03.domain.Genre;
import com.otus.spring03.service.BookService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringbootApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringbootApplication.class, args);

		Author author = Author.builder().author("Jules").surname("Verne").build();
		Genre genre = Genre.builder().genre("Historical novel").build();
		Book book = Book.builder()
				.bookName("Dick Sand, A Captain at Fifteen")
				.author(author)
				.genre(genre)
				.build();
		BookService bookService = context.getBean(BookService.class);
		bookService.insertBook(book);

		System.out.println(bookService.getBookById(2));;
		//        Console.main(args);
	}

}
