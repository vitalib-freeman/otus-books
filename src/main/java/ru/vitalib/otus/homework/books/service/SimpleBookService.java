package ru.vitalib.otus.homework.books.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vitalib.otus.homework.books.converter.BookConverter;
import ru.vitalib.otus.homework.books.dao.BookDao;
import ru.vitalib.otus.homework.books.domain.Author;
import ru.vitalib.otus.homework.books.domain.Book;
import ru.vitalib.otus.homework.books.domain.Comment;
import ru.vitalib.otus.homework.books.domain.Genre;
import ru.vitalib.otus.homework.books.dto.BookDto;
import ru.vitalib.otus.homework.books.exception.BookNotFoundException;
import ru.vitalib.otus.homework.books.exception.NotFoundException;

@Service
@AllArgsConstructor
public class SimpleBookService implements BookService {
  private final BookDao bookDao;
  private final GenreService genreService;
  private final AuthorService authorService;
  private final BookConverter bookConverter;

  @Override
  @Transactional
  public Long createBook(String title, String authorName, String genreName) {
    Author author = authorService.getAuthorByName(authorName);
    Genre genre = genreService.getGenreByName(genreName);
    Book book = new Book(title, genre, author);
    return bookDao.save(book).getId();
  }

  @Override
  @Transactional
  public void deleteBook(long id) {
    bookDao.delete(id);
  }

  @Override
  @Transactional
  public void updateBook(long id, String title, String authorName, String genreName) {
    Book book = bookDao.findById(id);
    if (book == null) {
      throw new NotFoundException();
    }
    Author author = authorService.getAuthorByName(authorName);
    Genre genre = genreService.getGenreByName(genreName);
    bookDao.update(book.getId(), new Book(title, genre, author));
  }

  @Override
  @Transactional
  public List<BookDto> getAllBooks() {
    return bookConverter.convert(bookDao.findAll());
  }

  @Override
  @Transactional
  public BookDto getBookById(long id) {
    return Optional.ofNullable(bookDao.findById(id))
        .map(bookConverter::convert)
        .orElseThrow(BookNotFoundException::new);
  }

  @Override
  @Transactional
  public void addCommentToBook(Long bookId, String commentText) {
    Book book = bookDao.findById(bookId);
    if (book == null) {
      throw new NotFoundException();
    }
    Comment comment = new Comment();
    comment.setBook(book);
    comment.setText(commentText);
    book.getComments().add(comment);
  }
}
