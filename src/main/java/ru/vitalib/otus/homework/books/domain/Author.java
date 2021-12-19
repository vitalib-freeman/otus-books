package ru.vitalib.otus.homework.books.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Author {
  private long id;
  private final String name;

  public Author(String name) {
    this.name = name;
  }

}
