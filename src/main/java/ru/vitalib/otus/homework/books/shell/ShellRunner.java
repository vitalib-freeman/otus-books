package ru.vitalib.otus.homework.books.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.vitalib.otus.homework.books.service.ViewService;

@ShellComponent
@RequiredArgsConstructor
public class ShellRunner {
  private final ViewService viewService;

  @ShellMethod(value = "View all books", key = {"all", "a"})
  public void viewAllBooks() {
    viewService.viewAllBooks();
  }

  @ShellMethod(value = "Add book", key = {"create", "c"})
  public void createBook(String bookTitle, String author, String genre) {
    viewService.createBook(bookTitle, author, genre);
  }

  @ShellMethod(value = "Update book", key = {"update", "u"})
  public void updateBook(Long bookId, String bookTitle, String author, String genre) {
    viewService.updateBook(bookId, bookTitle, author, genre);
  }

  @ShellMethod(value = "Delete book", key = {"delete", "d"})
  public void deleteBook(Long bookId) {
    viewService.deleteBook(bookId);
  }

  @ShellMethod(value = "View book", key = {"book", "b"})
  public void viewBook(Long bookId) {
    viewService.viewBook(bookId);
  }
}
