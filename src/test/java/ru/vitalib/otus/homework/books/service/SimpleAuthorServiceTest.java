package ru.vitalib.otus.homework.books.service;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.vitalib.otus.homework.books.domain.Author;
import ru.vitalib.otus.homework.books.dao.AuthorDao;

@DisplayName("Test author service")
@SpringBootTest(classes = {SimpleAuthorService.class})
class SimpleAuthorServiceTest {

  private static final String AUTHOR = "Author";
  @Autowired
  AuthorService authorService;

  @MockBean
  AuthorDao authorDao;

  @Test
  @DisplayName("Test can get author by name")
  public void getAuthorByName() {
    when(authorDao.findByName("Author")).thenReturn(Optional.of(new Author("Author")));

    Author author = authorService.getAuthorByName(AUTHOR);

    assertThat(author).isNotNull();
  }
}