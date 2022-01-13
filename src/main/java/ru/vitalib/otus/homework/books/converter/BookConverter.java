package ru.vitalib.otus.homework.books.converter;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import ru.vitalib.otus.homework.books.domain.Author;
import ru.vitalib.otus.homework.books.domain.Book;
import ru.vitalib.otus.homework.books.domain.Comment;
import ru.vitalib.otus.homework.books.domain.Genre;
import ru.vitalib.otus.homework.books.dto.AuthorDto;
import ru.vitalib.otus.homework.books.dto.BookDto;
import ru.vitalib.otus.homework.books.dto.CommentDto;
import ru.vitalib.otus.homework.books.dto.GenreDto;

@Component
public class BookConverter {
  public List<BookDto> convert(List<Book> books) {
    return books.stream()
        .map(this::convert)
        .collect(Collectors.toList());
  }

  public BookDto convert(Book book) {
    return new BookDto()
        .setAuthorDto(convert(book.getAuthor()))
        .setGenreDto(convert(book.getGenre()))
        .setId(book.getId())
        .setName(book.getName())
        .setComments(convertComments(book.getComments()));
  }

  public List<CommentDto> convertComments(List<Comment> comments) {
    return comments.stream()
        .map(this::convertComment)
        .collect(Collectors.toList());
  }

  private CommentDto convertComment(Comment comment) {
    return new CommentDto()
        .setId(comment.getId())
        .setText(comment.getText());
  }

  private GenreDto convert(Genre genre) {
    return new GenreDto()
        .setId(genre.getId())
        .setName(genre.getName());
  }

  private AuthorDto convert(Author author) {
    return new AuthorDto()
        .setId(author.getId())
        .setName(author.getName());
  }
}
