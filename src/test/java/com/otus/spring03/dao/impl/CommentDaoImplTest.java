package com.otus.spring03.dao.impl;

import com.otus.spring03.model.Book;
import com.otus.spring03.model.Comment;
import com.otus.spring03.model.Genre;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@Import(CommentDaoImpl.class)
class CommentDaoImplTest {

  private static final String COMMENT = "Some comment";

  @Autowired
  private CommentDaoImpl commentDao;

  @Autowired
  private TestEntityManager tem;

  @Test
  void insert() {
    val actual = commentDao.insert(Comment.builder().comment(COMMENT).book(Book.builder().id(1L).build()).build());
    val expected = tem.find(Comment.class, actual.getId());

    assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
  }

  @Test
  void delete() {
    val comment = commentDao.findById(1L);
    commentDao.delete(comment);

    val expected = commentDao.findById(1L);
    assertNull(expected);
  }

  @Test
  void update() {
    val comment = tem.find(Comment.class, 1L);
    comment.setComment(COMMENT);
    commentDao.update(comment);
    val actual = commentDao.findById(1L);

    assertNotNull(actual);
    assertEquals(COMMENT, actual.getComment());
  }

  @Test
  void findById() {
    val actual = commentDao.findById(1);
    val expected = tem.find(Comment.class, 1L);

    assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
  }
}