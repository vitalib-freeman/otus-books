package ru.vitalib.otus.homework.books.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.vitalib.otus.homework.books.dao.AuthorDao;
import ru.vitalib.otus.homework.books.dao.BookDao;
import ru.vitalib.otus.homework.books.dao.GenreDao;
import ru.vitalib.otus.homework.books.domain.Author;
import ru.vitalib.otus.homework.books.domain.Book;
import ru.vitalib.otus.homework.books.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Test book service")
@SpringBootTest(classes = {SimpleBookService.class})
class SimpleBookServiceTest {

  @Autowired
  BookService bookService;

  @MockBean
  BookDao bookDao;
  @MockBean
  AuthorDao authorDao;
  @MockBean
  GenreDao genreDao;

  public static final Author EXISTING_AUTHOR = new Author(1, "Веллер Михаил");
  public static final Genre EXISTING_GENRE = new Genre(1, "Детектив");
  public static final Book EXISTING_BOOK = new Book(1, "Хочу быть дворником", EXISTING_GENRE, EXISTING_AUTHOR);

  @Test
  @DisplayName("Get all books")
  void getAllBooks() {
    when(bookDao.findAll()).thenReturn(List.of(EXISTING_BOOK));
    List<Book> allBooks = bookService.getAllBooks();

    assertThat(allBooks)
        .containsAll(List.of(EXISTING_BOOK));
  }

  @Test
  void saveBookWithExistentGenreAndAuthor() {
    when(authorDao.findByName(EXISTING_AUTHOR.getName())).thenReturn(EXISTING_AUTHOR);
    when(genreDao.findByName(EXISTING_GENRE.getName())).thenReturn(EXISTING_GENRE);

    Long savedBookId = bookService.createBook("New Book Title", EXISTING_AUTHOR.getName(), EXISTING_GENRE.getName());

    verify(authorDao).findByName(EXISTING_AUTHOR.getName());
    verify(genreDao).findByName(EXISTING_GENRE.getName());
    verify(bookDao).save(any());
    assertThat(savedBookId).isNotEqualTo(EXISTING_BOOK.getId());
  }

  @Test
  void saveBookWithNonExistentGenreAndAuthor() {
    when(authorDao.findByName(EXISTING_AUTHOR.getName())).thenReturn(null);
    when(genreDao.findByName(EXISTING_GENRE.getName())).thenReturn(null);

    Long savedBookId = bookService.createBook("New Book Title", EXISTING_AUTHOR.getName(), EXISTING_GENRE.getName());

    verify(authorDao).findByName(EXISTING_AUTHOR.getName());
    verify(authorDao).save(any());
    verify(genreDao).findByName(EXISTING_GENRE.getName());
    verify(genreDao).save(any());
    verify(bookDao).save(any());
    assertThat(savedBookId).isNotEqualTo(EXISTING_BOOK.getId());
  }
}