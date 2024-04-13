package com.mvc.classic.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraph(name = "book-genre-eg",
    attributeNodes = { @NamedAttributeNode("genre"), @NamedAttributeNode("author") })
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "book_name")
  private String bookName;

  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(name = "books_genre",
      joinColumns = { @JoinColumn(name = "books_id") },
      inverseJoinColumns = { @JoinColumn(name = "genre_id") })
  private List<Genre> genre;

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "author_id")
  private Author author;

  @OneToMany(mappedBy = "book", targetEntity = Comment.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  private List<Comment> comments;
}
