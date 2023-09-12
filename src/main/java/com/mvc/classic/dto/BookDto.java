package com.mvc.classic.dto;

import com.mvc.classic.domain.Book;
import com.mvc.classic.domain.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.util.List;

@Data
@AllArgsConstructor
public class BookDto {

    @Nullable
    private long id;
    private String name;
    private String author;
    private String genre;


    public Book toDomainObject(){
        return Book.builder().build();
    }

    public static BookDto fromDomainObject(Book book) {
        return new BookDto(book.getId(), book.getBookName(), book.getAuthor().getAuthor(), book.getGenre().get(0).getGenre());
    }


}
