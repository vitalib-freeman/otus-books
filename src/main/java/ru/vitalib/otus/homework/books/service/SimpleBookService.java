package ru.vitalib.otus.homework.books.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vitalib.otus.homework.books.dao.AuthorDao;
import ru.vitalib.otus.homework.books.dao.BookDao;
import ru.vitalib.otus.homework.books.dao.GenreDao;
import ru.vitalib.otus.homework.books.domain.Author;
import ru.vitalib.otus.homework.books.domain.Book;
import ru.vitalib.otus.homework.books.domain.Genre;

import java.util.List;

@Service
@AllArgsConstructor
public class SimpleBookService implements BookService {
  private final BookDao bookDao;
  private final AuthorDao authorDao;
  private final GenreDao genreDao;

  @Override
  public Long createBook(String title, String authorName, String genreName) {
    Author author = authorDao.findByName(authorName);
    if (author == null) {
      author = new Author(authorName);
      authorDao.save(author);
    }
    Genre genre = genreDao.findByName(genreName);
    if (genre == null) {
      genre = new Genre(genreName);
      genreDao.save(genre);
    }
    Book book = new Book(title, genre, author);
    return bookDao.save(book);
  }

  @Override
  public void deleteBook(long id) {

  }

  @Override
  public void updateBook(long id, String title, String author, String genre) {

  }

  @Override
  public List<Book> getAllBooks() {
    return bookDao.findAll();
  }
}
