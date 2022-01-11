package ru.vitalib.otus.homework.books.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Data
@Entity
@Table(name = "book")
@AllArgsConstructor
@NoArgsConstructor
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "name")
  private String name;

  @OneToOne(targetEntity = Genre.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "genre_id")
  private Genre genre;

  @ManyToOne(targetEntity = Author.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "author_id")
  private Author author;

  @Fetch(FetchMode.SUBSELECT)
  @OneToMany(mappedBy="book", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<Comment> comments = new ArrayList<>();

  public Book(String name, Genre genre, Author author) {
    this.name = name;
    this.genre = genre;
    this.author = author;
  }

  public Book(long id, String name, Genre genre, Author author) {
    this.id = id;
    this.name = name;
    this.genre = genre;
    this.author = author;
  }
}
