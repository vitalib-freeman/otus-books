package ru.vitalib.otus.homework.books.dao;

import static org.assertj.core.api.Assertions.assertThat;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class BookDaoTest {

  @Autowired
  TestEntityManager em;
  @Autowired
  private BookDao bookDao;

  @Test
  @DisplayName("Get all books with all information")
  void getAll() {
    SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
        .unwrap(SessionFactory.class);
    sessionFactory.getStatistics().setStatisticsEnabled(true);

    assertThat(bookDao.findAll()).isNotNull().hasSize(1)
        .allMatch(b -> !b.getName().equals(""))
        .allMatch(b -> b.getAuthor() != null)
        .allMatch(b -> b.getGenre() != null)
        .allMatch(b -> b.getComments().size() > 0)
        .hasSize(1);
    assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(2);
  }
}