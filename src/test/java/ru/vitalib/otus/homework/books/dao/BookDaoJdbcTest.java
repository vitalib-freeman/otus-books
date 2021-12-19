package ru.vitalib.otus.homework.books.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.vitalib.otus.homework.books.domain.Book;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(BookDaoJdbc.class)
class BookDaoJdbcTest {

  public static final int EXISTING_BOOKS_COUNT = 1;
  public static final Book EXISTING_BOOK = new Book(1, "Хочу быть дворником");

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
    Book updatedBook = new Book(EXISTING_BOOK.getId(), "Пушкин Александр");

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
    Book book = new Book(2, "Толстой Лев");

    dao.save(book);

    assertThat(dao.findById(book.getId()))
        .usingRecursiveComparison()
        .isEqualTo(book);
  }
}