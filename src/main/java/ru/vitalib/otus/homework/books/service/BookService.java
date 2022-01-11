package ru.vitalib.otus.homework.books.service;


import java.util.List;
import ru.vitalib.otus.homework.books.domain.Book;
import ru.vitalib.otus.homework.books.dto.BookDto;

public interface BookService {
  Long createBook(String title, String author, String genre);

  void deleteBook(long id);

  void updateBook(long id, String title, String author, String genre);

  List<BookDto> getAllBooks();

  BookDto getBookById(long id);
}
