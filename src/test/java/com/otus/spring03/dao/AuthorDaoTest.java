package com.otus.spring03.dao;

import com.otus.spring03.model.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Это ж уже интеграционное тестирование
@DataMongoTest
@ExtendWith(SpringExtension.class)
class AuthorDaoTest {

  @Autowired
  private MongoTemplate mongoTemplate;

  private Author author;

  @BeforeEach
  public void beforeEach() {
    author = Author.builder().id(1L).author("Author").surname("Surname").build();
    mongoTemplate.save(author, "testLibrary");
  }

  @Test
  void findAll() {
    var actual = mongoTemplate.findAll(Author.class, "testLibrary");
    assertEquals(1, actual.size());
    assertEquals(author.getAuthor(), actual.get(0).getAuthor());
  }

}