package ru.vitalib.otus.homework.books.service;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.vitalib.otus.homework.books.domain.Genre;
import ru.vitalib.otus.homework.books.dao.GenreDao;

@SpringBootTest(classes = {SimpleGenreService.class})
@DisplayName("Test simple genre service")
class SimpleGenreServiceTest {

  @Autowired
  private SimpleGenreService genreService;

  @MockBean
  private GenreDao genreDao;

  @Test
  @DisplayName("Test fetching genre by name")
  public void getGenreByName() {
    when(genreDao.findByName("Genre")).thenReturn(new Genre("Genre"));

    Genre genre = genreService.getGenreByName("Genre");

    assertThat(genre).isNotNull();
  }
}