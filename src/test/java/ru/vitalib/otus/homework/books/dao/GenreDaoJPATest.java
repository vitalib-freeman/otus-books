package ru.vitalib.otus.homework.books.dao;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.vitalib.otus.homework.books.dao.GenreDaoJPA;
import ru.vitalib.otus.homework.books.domain.Genre;

@DisplayName("GenreDao test")
@DataJpaTest
@Import(GenreDaoJPA.class)
class GenreDaoJPATest {

  @Autowired
  private TestEntityManager em;

  public static final int EXISTING_GENRES_COUNT = 1;
  public static final Genre EXISTING_GENRE = new Genre(1, "Детектив");

  @Autowired
  private GenreDaoJPA genreRepositoryJPA;

  @Test
  @DisplayName("Find genre by id")
  void findById() {
    assertThat(genreRepositoryJPA.findById(1)).usingRecursiveComparison()
        .isEqualTo(EXISTING_GENRE);
  }

  @Test
  @DisplayName("Get genre's count")
  void getCount() {
    assertThat(genreRepositoryJPA.count()).isEqualTo(EXISTING_GENRES_COUNT);
  }

  @Test
  @DisplayName("Delete genre")
  void delete() {
    Genre genreForDeletion = genreRepositoryJPA.save(new Genre("Other genre"));
    assertThat(genreRepositoryJPA.count()).isEqualTo(EXISTING_GENRES_COUNT+1);

    genreRepositoryJPA.delete(genreForDeletion.getId());

    assertThat(genreRepositoryJPA.count()).isEqualTo(EXISTING_GENRES_COUNT);
  }

  @Test
  @DisplayName("Update genre name")
  void update() {
    Genre updatedGenre = new Genre(EXISTING_GENRE.getId(), "Пушкин Александр");

    genreRepositoryJPA.save(updatedGenre);

    assertThat(em.find(Genre.class, EXISTING_GENRE.getId()))
        .usingRecursiveComparison()
        .isEqualTo(updatedGenre);
  }

  @Test
  @DisplayName("Get all genres")
  void getAll() {
    assertThat(genreRepositoryJPA.findAll())
        .usingRecursiveComparison()
        .isEqualTo(List.of(EXISTING_GENRE));
  }

  @Test
  @DisplayName("Save genre")
  void save() {
    Genre genre = new Genre("Толстой Лев");

    Genre savedGenre = genreRepositoryJPA.save(genre);

    assertThat(em.find(Genre.class, savedGenre.getId()))
        .extracting("name")
        .isEqualTo("Толстой Лев");
  }
}