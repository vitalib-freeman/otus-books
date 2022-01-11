package ru.vitalib.otus.homework.books.dto;

import lombok.Getter;

@Getter
public class AuthorDto {
  private long id;
  private String name;

  public AuthorDto setId(long id) {
    this.id = id;
    return this;
  }

  public AuthorDto setName(String name) {
    this.name = name;
    return this;
  }
}
