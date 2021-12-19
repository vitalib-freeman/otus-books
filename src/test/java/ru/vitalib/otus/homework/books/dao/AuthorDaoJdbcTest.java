package ru.vitalib.otus.homework.books.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.vitalib.otus.homework.books.domain.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(AuthorDaoJdbc.class)
class AuthorDaoJdbcTest {

  public static final int EXISTING_AUTHORS_COUNT = 1;
  public static final Author EXISTING_AUTHOR = new Author(1, "Веллер Михаил");

  @Autowired
  private AuthorDaoJdbc dao;

  @Test
  @DisplayName("Find author by id")
  void findById() {
    assertThat(dao.findById(1)).usingRecursiveComparison()
        .isEqualTo(EXISTING_AUTHOR);
  }

  @Test
  @DisplayName("Get author's count")
  void getCount() {
    assertThat(dao.count()).isEqualTo(EXISTING_AUTHORS_COUNT);
  }

  @Test
  @DisplayName("Delete author")
  void delete() {
    Author newAuthor = new Author(2, "New author");
    dao.save(newAuthor);
    assertThat(dao.count()).isEqualTo(EXISTING_AUTHORS_COUNT + 1);

    dao.delete(newAuthor.getId());

    assertThat(dao.count()).isEqualTo(EXISTING_AUTHORS_COUNT);
  }

  @Test
  @DisplayName("Update author name")
  void update() {
    Author updatedAuthor = new Author(EXISTING_AUTHOR.getId(), "Пушкин Александр");

    dao.update(EXISTING_AUTHOR.getId(), updatedAuthor);

    assertThat(dao.findById(EXISTING_AUTHOR.getId()))
        .usingRecursiveComparison()
        .isEqualTo(updatedAuthor);
  }

  @Test
  @DisplayName("Get all authors")
  void getAll() {
    assertThat(dao.findAll())
        .usingRecursiveComparison()
        .isEqualTo(List.of(EXISTING_AUTHOR));
  }

  @Test
  @DisplayName("Save author")
  void save() {
    Author author = new Author("Толстой Лев");

    long savedAuthorId = dao.save(author);

    assertThat(dao.findById(savedAuthorId))
        .extracting("name")
        .isEqualTo("Толстой Лев");
  }

  @Test
  void findByName() {
    assertThat(dao.findByName("Веллер Михаил"))
        .isEqualTo(EXISTING_AUTHOR);
  }
}