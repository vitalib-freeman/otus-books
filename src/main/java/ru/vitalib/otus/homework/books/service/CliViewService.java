package ru.vitalib.otus.homework.books.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vitalib.otus.homework.books.dto.BookDto;
import ru.vitalib.otus.homework.books.dto.CommentDto;
import ru.vitalib.otus.homework.books.exception.AuthorNotFoundException;
import ru.vitalib.otus.homework.books.exception.BookNotFoundException;
import ru.vitalib.otus.homework.books.exception.GenreNotFoundException;

@Service
@AllArgsConstructor
public class CliViewService implements ViewService {
  public final BookService bookService;

  @Override
  public void createBook(String bookName, String authorName, String genreName) {
    Long bookId;
    try {
      bookId = bookService.createBook(bookName, authorName, genreName);
      System.out.println(String.format("Book with id %d is created", bookId));
    } catch (BookNotFoundException ex) {
      System.out.println("Error: Book doesn't exist");
    } catch (GenreNotFoundException ex) {
      System.out.println("Error: Genre doesn't exist");
    } catch (AuthorNotFoundException ex) {
      System.out.println("Error: Author doesn't exist");
    }
  }

  @Override
  public void viewAllBooks() {
    List<BookDto> allBooks = bookService.getAllBooks();
    if (allBooks.isEmpty()) {
      System.out.println("No books found");
      return;
    }
    allBooks.forEach(this::printBook);
  }

  private void printBook(BookDto bookDto) {
    System.out.println(
        String.format("Id: %d, Title: %s, Author: %s, Genre: %s, Comments: %s",
            bookDto.getId(),
            bookDto.getName(),
            bookDto.getAuthorDto().getName(),
            bookDto.getGenreDto().getName(),
            bookDto.getComments().stream().map(CommentDto::getText).collect(Collectors.joining(", "))
        )
    );
  }

  @Override
  public void updateBook(Long bookId, String bookName, String authorName, String genreName) {
    try {
      bookService.updateBook(bookId, bookName, authorName, genreName);
      System.out.println(String.format("Book with id %d is updated", bookId));
    } catch (BookNotFoundException ex) {
      System.out.println("Error: Book doesn't exist");
    } catch (GenreNotFoundException ex) {
      System.out.println("Error: Genre doesn't exist");
    } catch (AuthorNotFoundException ex) {
      System.out.println("Error: Author doesn't exist");
    }
  }

  @Override
  public void deleteBook(Long bookId) {
    bookService.deleteBook(bookId);
    System.out.println("Book successfully deleted");
  }

  @Override
  public void viewBook(Long bookId) {
    try {
      printBook(bookService.getBookById(bookId));
    } catch (BookNotFoundException ex) {
      System.out.println("Error: Book doesn't exist");
    }
  }

  @Override
  public void commentBook(Long bookId, String commentText) {
    bookService.commentBook(bookId, commentText);
  }
}
