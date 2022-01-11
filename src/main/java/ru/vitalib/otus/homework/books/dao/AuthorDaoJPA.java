package ru.vitalib.otus.homework.books.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import ru.vitalib.otus.homework.books.domain.Author;

@Repository
public class AuthorDaoJPA implements AuthorDao {
  @PersistenceContext
  private final EntityManager em;

  public AuthorDaoJPA(EntityManager em) {
    this.em = em;
  }

  @Transactional
  @Override
  public Author save(Author author) {
    if (author.getId() == 0) {
      em.persist(author);
      return author;
    }
    return em.merge(author);
  }

  @Override
  public Author findById(long id) {
    return em.find(Author.class, id);
  }

  @Override
  public void delete(long id) {
    Query query = em.createQuery("delete from Author a where a.id = :id");
    query.setParameter("id", id);
    query.executeUpdate();
  }

  @Override
  public long count() {
    return em.createQuery("select count(a) from Author  a", Long.class)
        .getSingleResult();
  }

  @Override
  public List<Author> findAll() {
    TypedQuery<Author> query = em.createQuery("select a from Author a", Author.class);
    return query.getResultList();
  }

  @Override
  public Author findByName(String authorName) {
    TypedQuery<Author> query = em.createQuery("select a from Author a where a.name = :authorName", Author.class);
    query.setParameter("authorName", authorName);
    return query.getSingleResult();
  }
}
