package ru.vitalib.otus.homework.books.dao;

import static org.assertj.core.api.Assertions.assertThat;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.vitalib.otus.homework.books.domain.Author;
import ru.vitalib.otus.homework.books.domain.Book;
import ru.vitalib.otus.homework.books.domain.Genre;

@DataJpaTest
@Import(BookDaoJPA.class)
class BookDaoJPATest {

  public static final int EXISTING_BOOKS_COUNT = 1;
  public static final Author EXISTING_AUTHOR = new Author(1, "Веллер Михаил");
  public static final Genre EXISTING_GENRE = new Genre(1, "Детектив");
  public static final Book EXISTING_BOOK = new Book(1, "Хочу быть дворником", EXISTING_GENRE, EXISTING_AUTHOR);
  public static final long NON_EXISTING_BOOK_ID = 10000L;
  private static final int EXISTING_BOOK_ID = 1;

  @Autowired
  private TestEntityManager em;

  @Autowired
  private BookDaoJPA bookDaoJPA;

  @Test
  @DisplayName("Find book by id")
  void findById() {
    assertThat(bookDaoJPA.findById(EXISTING_BOOK_ID))
        .isNotNull()
        .matches(b -> b.getName().equals("Хочу быть дворником"))
        .matches(b -> b.getAuthor() != null && b.getAuthor().getName().equals("Веллер Михаил"))
        .matches(b -> b.getGenre() != null && b.getGenre().getName().equals("Детектив"));
  }

  @Test
  @DisplayName("Get book's count")
  void getCount() {
    assertThat(bookDaoJPA.count()).isEqualTo(EXISTING_BOOKS_COUNT);
  }

  @Test
  @DisplayName("Delete book")
  void delete() {
    Book forDeletion = bookDaoJPA.save(new Book("new", EXISTING_GENRE, EXISTING_AUTHOR));
    assertThat(bookDaoJPA.count()).isEqualTo(EXISTING_BOOKS_COUNT + 1);

    bookDaoJPA.delete(forDeletion.getId());

    assertThat(bookDaoJPA.count()).isEqualTo(EXISTING_BOOKS_COUNT);
  }

  @Test
  @DisplayName("Update book name")
  void update() {
    Book updatedBook = new Book(EXISTING_BOOK.getId(), "Паранойа", EXISTING_GENRE, EXISTING_AUTHOR);

    bookDaoJPA.save(updatedBook);

    assertThat(em.find(Book.class, EXISTING_BOOK.getId()))
        .matches(b -> b.getName().equals("Паранойа"));
  }

  @Test
  @DisplayName("Get all books with all information")
  void getAll() {
    SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
        .unwrap(SessionFactory.class);
    sessionFactory.getStatistics().setStatisticsEnabled(true);

    assertThat(bookDaoJPA.findAll()).isNotNull().hasSize(1)
        .allMatch(b -> !b.getName().equals(""))
        .allMatch(b -> b.getAuthor() != null)
        .allMatch(b -> b.getGenre() != null)
        .allMatch(b -> b.getComments().size() > 0)
        .hasSize(1);
    assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(2);
  }

  @Test
  @DisplayName("Save book")
  void save() {
    Book book = new Book("Артиллерист", EXISTING_GENRE, EXISTING_AUTHOR);

    Book savedBook = bookDaoJPA.save(book);

    assertThat(em.find(Book.class, savedBook.getId()))
        .isNotNull().matches(b -> !b.getName().equals(""))
        .matches(b -> b.getAuthor() != null)
        .matches(b -> b.getGenre() != null)
        .matches(b -> b.getName().equals("Артиллерист"));
  }

  @Test
  @DisplayName("Find for non-existing book should return")
  public void getNonExistentBook() {
    assertThat(em.find(Book.class, NON_EXISTING_BOOK_ID)).isEqualTo(null);
  }
}