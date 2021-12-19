package ru.vitalib.otus.homework.books.domain;

import lombok.Data;

@Data
public class Book {
  private final long id;
  private final String name;
  private final Genre genre;
  private final Author author;
}
