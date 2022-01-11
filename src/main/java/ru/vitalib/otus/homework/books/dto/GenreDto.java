package ru.vitalib.otus.homework.books.dto;

import lombok.Getter;

@Getter
public class GenreDto {
  private long id;
  private String name;

  public GenreDto setId(long id) {
    this.id = id;
    return this;
  }

  public GenreDto setName(String name) {
    this.name = name;
    return this;
  }
}
