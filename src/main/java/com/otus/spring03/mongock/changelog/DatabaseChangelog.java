package com.otus.spring03.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import com.otus.spring03.dao.AuthorDao;
import com.otus.spring03.dao.BookDao;
import com.otus.spring03.dao.CommentDao;
import com.otus.spring03.dao.GenreDao;
import com.otus.spring03.model.Author;
import com.otus.spring03.model.Book;
import com.otus.spring03.model.Comment;
import com.otus.spring03.model.Genre;

import java.util.Collections;

@ChangeLog
public class DatabaseChangelog {

  @ChangeSet(order = "001", id = "dropDB", author = "Beka", runAlways = true)
  public void dropDB(MongoDatabase md) {
    md.drop();
  }

  @ChangeSet(order = "002", id = "insertData", author = "Beka", runAlways = true)
  public void insertData(CommentDao commentDao, GenreDao genreDao, AuthorDao authorDao, BookDao bookDao) {
    Comment comment = Comment.builder().id(1L).comment("Comment of book 1").bookId(1L).build();
    commentDao.save(comment);

    Genre genre = Genre.builder().id(1L).genre("Genre").build();
    genreDao.save(genre);

    Author author = Author.builder().id(1L).author("Author").surname("Surname").build();
    authorDao.save(author);

    Book book = Book.builder().id(1L).bookName("Book").author(author).genreIds(Collections.singletonList(1l)).build();
    bookDao.save(book);
  }
}
