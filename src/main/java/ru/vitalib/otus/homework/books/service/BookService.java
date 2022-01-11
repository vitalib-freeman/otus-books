package ru.vitalib.otus.homework.books.service;


import java.util.List;
import ru.vitalib.otus.homework.books.domain.Book;

public interface BookService {
  Long createBook(String title, String author, String genre);

  void deleteBook(long id);

  void updateBook(long id, String title, String author, String genre);

  List<Book> getAllBooks();

  Book getBookById(long id);
}
