package ru.vitalib.otus.homework.books.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.vitalib.otus.homework.books.domain.Author;
import ru.vitalib.otus.homework.books.domain.Book;
import ru.vitalib.otus.homework.books.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@JdbcTest
@Import(BookDaoJdbc.class)
class BookDaoJdbcTest {

  public static final int EXISTING_BOOKS_COUNT = 1;
  public static final Author EXISTING_AUTHOR = new Author(1, "Веллер Михаил");
  public static final Genre EXISTING_GENRE = new Genre(1, "Детектив");
  public static final Book EXISTING_BOOK = new Book(1, "Хочу быть дворником", EXISTING_GENRE, EXISTING_AUTHOR);
  public static final int NON_EXISTING_BOOK_ID = 10000;

  @Autowired
  private BookDaoJdbc dao;

  @Test
  @DisplayName("Find book by id")
  void findById() {
    assertThat(dao.findById(1)).usingRecursiveComparison()
        .isEqualTo(EXISTING_BOOK);
  }

  @Test
  @DisplayName("Get book's count")
  void getCount() {
    assertThat(dao.count()).isEqualTo(EXISTING_BOOKS_COUNT);
  }

  @Test
  @DisplayName("Delete book")
  void delete() {
    dao.delete(EXISTING_BOOK.getId());

    assertThat(dao.count()).isEqualTo(EXISTING_BOOKS_COUNT - 1);
  }

  @Test
  @DisplayName("Update book name")
  void update() {
    Book updatedBook = new Book(EXISTING_BOOK.getId(), "Паранойа", EXISTING_GENRE, EXISTING_AUTHOR);

    dao.update(EXISTING_BOOK.getId(), updatedBook);

    assertThat(dao.findById(EXISTING_BOOK.getId()))
        .usingRecursiveComparison()
        .isEqualTo(updatedBook);
  }

  @Test
  @DisplayName("Get all books")
  void getAll() {
    assertThat(dao.findAll())
        .usingRecursiveComparison()
        .isEqualTo(List.of(EXISTING_BOOK));
  }

  @Test
  @DisplayName("Save book")
  void save() {
    Book book = new Book("Толстой Лев", EXISTING_GENRE, EXISTING_AUTHOR);

    Book savedBook = dao.save(book);

    assertThat(dao.findById(savedBook.getId()))
        .extracting("name")
        .isEqualTo("Толстой Лев");
  }

  @Test
  @DisplayName("Find for non-existing book should return")
  public void getNonExistentBook() {
    assertThat(dao.findById(NON_EXISTING_BOOK_ID)).isEqualTo(null);
  }

}