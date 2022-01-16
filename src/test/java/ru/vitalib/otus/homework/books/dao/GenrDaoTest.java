package ru.vitalib.otus.homework.books.dao;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.vitalib.otus.homework.books.domain.Genre;

@DisplayName("GenreDao test")
@DataJpaTest
class GenreDaoTest {

  public static final Genre EXISTING_GENRE = new Genre(1, "Детектив");
  @Autowired
  private GenreDao genreDao;

  @Test
  @DisplayName("Find genre by name")
  void findGenreByName() {
    assertThat(genreDao.findByName("Детектив").get()).usingRecursiveComparison()
        .isEqualTo(EXISTING_GENRE);
  }
}