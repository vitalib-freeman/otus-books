package ru.vitalib.otus.homework.books.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import ru.vitalib.otus.homework.books.domain.Book;

@Repository
public class BookDaoJPA implements BookDao {

  private final EntityManager em;

  public BookDaoJPA(EntityManager em) {
    this.em = em;
  }

  @Override
  public Book save(Book book) {
    if (book.getId() == 0) {
      em.persist(book);
      return book;
    }
    return em.merge(book);
  }

  @Override
  public Book findById(long id) {
    return em.find(Book.class, id);
  }

  @Override
  public void delete(long id) {
    Query query = em.createQuery("delete from Book b where b.id = :id");
    query.setParameter("id", id);
    query.executeUpdate();
  }

  @Override
  public void update(long id, Book author) {

  }

  @Override
  public long count() {
    return em.createQuery("select count(b) from Book b", Long.class).getSingleResult();
  }

  @Override
  public List<Book> findAll() {
    return em.createQuery("select b from Book b join fetch b.author join fetch b.genre", Book.class).getResultList();
  }
}
