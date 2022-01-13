package ru.vitalib.otus.homework.books.dao;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.vitalib.otus.homework.books.domain.Author;

@DataJpaTest
@Import(AuthorDaoJPA.class)
class AuthorDaoJPATest {

  public static final int EXISTING_AUTHORS_COUNT = 1;
  public static final Author EXISTING_AUTHOR = new Author(1, "Веллер Михаил");
  @Autowired
  private TestEntityManager em;
  @Autowired
  private AuthorDaoJPA authorRepositoryJPA;

  @Test
  @DisplayName("Find author by id")
  void findById() {
    assertThat(authorRepositoryJPA.findById(1)).usingRecursiveComparison()
        .isEqualTo(EXISTING_AUTHOR);
  }

  @Test
  @DisplayName("Get author's count")
  void getCount() {
    assertThat(authorRepositoryJPA.count()).isEqualTo(EXISTING_AUTHORS_COUNT);
  }

  @Test
  @DisplayName("Delete author")
  void delete() {
    Author newAuthor = authorRepositoryJPA.save(new Author(2, "New author"));
    assertThat(authorRepositoryJPA.count()).isEqualTo(EXISTING_AUTHORS_COUNT + 1);

    authorRepositoryJPA.delete(newAuthor);

    assertThat(authorRepositoryJPA.count()).isEqualTo(EXISTING_AUTHORS_COUNT);
  }

  @Test
  @DisplayName("Update author name")
  void update() {
    Author updatedAuthor = new Author(EXISTING_AUTHOR.getId(), "Пушкин Александр");

    authorRepositoryJPA.save(updatedAuthor);

    assertThat(em.find(Author.class, EXISTING_AUTHOR.getId()))
        .usingRecursiveComparison()
        .isEqualTo(updatedAuthor);
  }

  @Test
  @DisplayName("Get all authors")
  void getAll() {
    assertThat(authorRepositoryJPA.findAll())
        .usingRecursiveComparison()
        .isEqualTo(List.of(EXISTING_AUTHOR));
  }

  @Test
  @DisplayName("Save author")
  void save() {
    Author author = new Author("Толстой Лев");

    Author savedAuthor = authorRepositoryJPA.save(author);

    assertThat(em.find(Author.class, savedAuthor.getId()))
        .matches(a -> a.getId() != 0 && a.getName().equals("Толстой Лев"));
  }

  @Test
  void findByName() {
    assertThat(authorRepositoryJPA.findByName("Веллер Михаил"))
        .isEqualTo(EXISTING_AUTHOR);
  }
}