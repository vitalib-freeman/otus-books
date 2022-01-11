package ru.vitalib.otus.homework.books.repositories;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import ru.vitalib.otus.homework.books.domain.Genre;

@Repository
public class GenreRepositoryJPA implements GenreRepository {

  @PersistenceContext
  private final EntityManager em;

  public GenreRepositoryJPA(EntityManager em) {
    this.em = em;
  }

  @Override
  public Genre save(Genre genre) {
    if (genre.getId() == 0) {
      em.persist(genre);
      return genre;
    }
    return em.merge(genre);
  }

  @Override
  public Genre findById(long id) {
    return em.find(Genre.class, id);
  }

  @Override
  public void delete(long id) {
    Query query = em.createQuery("delete from Genre g where g.id = :id");
    query.setParameter("id", id);
    query.executeUpdate();
  }

  @Override
  public long count() {
    return em.createQuery("select count(g) from Genre g", Long.class).getSingleResult();
  }

  @Override
  public List<Genre> findAll() {
    return em.createQuery("select g from Genre g", Genre.class).getResultList();
  }

  @Override
  public Genre findByName(String genreName) {
    return null;
  }
}
