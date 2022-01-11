package ru.vitalib.otus.homework.books.dto;

import lombok.Getter;

@Getter
public class BookDto {
  private long id;
  private String name;
  private AuthorDto authorDto;
  private GenreDto genreDto;

  public BookDto setId(long id) {
    this.id = id;
    return this;
  }

  public BookDto setName(String name) {
    this.name = name;
    return this;
  }

  public BookDto setAuthorDto(AuthorDto authorDto) {
    this.authorDto = authorDto;
    return this;
  }

  public BookDto setGenreDto(GenreDto genreDto) {
    this.genreDto = genreDto;
    return this;
  }
}
