package ru.vitalib.otus.homework.books.dto;

import lombok.Getter;

@Getter
public class CommentDto {
  private Long id;
  private String text;

  public CommentDto setId(Long id) {
    this.id = id;
    return this;
  }

  public CommentDto setText(String text) {
    this.text = text;
    return this;
  }
}
