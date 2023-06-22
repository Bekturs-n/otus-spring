package com.otus.spring03.domain;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
@Builder
public class Book {
    private final long id;
    private final String bookName;
    private final Author author;
    private final Genre genre;
}
