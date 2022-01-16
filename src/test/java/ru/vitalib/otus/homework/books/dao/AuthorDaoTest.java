package ru.vitalib.otus.homework.books.dao;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.vitalib.otus.homework.books.domain.Author;

@DataJpaTest
class AuthorDaoTest {

  public static final Author EXISTING_AUTHOR = new Author(1, "Веллер Михаил");
  @Autowired
  private AuthorDao authorDao;

  @Test
  void findByName() {
    assertThat(authorDao.findByName("Веллер Михаил").get())
        .isEqualTo(EXISTING_AUTHOR);
  }
}