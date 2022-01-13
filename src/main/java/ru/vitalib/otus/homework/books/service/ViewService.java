package ru.vitalib.otus.homework.books.service;

public interface ViewService {
  void createBook(String bookName, String authorName, String genreName);

  void viewAllBooks();

  void updateBook(Long bookId, String bookName, String authorName, String genreName);

  void deleteBook(Long bookId);

  void viewBook(Long bookId);

  void addCommentToBook(Long bookId, String commentText);

  void getBookComments(Long bookId);
}
