package ru.vitalib.otus.homework.books.dao;

import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import ru.vitalib.otus.homework.books.domain.Comment;

@Repository
public class CommentDaoJPA implements CommentDao {
  private final EntityManager em;

  public CommentDaoJPA(EntityManager em) {
    this.em = em;
  }

  @Override
  public Comment save(Comment comment) {
    if (comment.getId() == 0) {
      em.persist(comment);
      return comment;
    }
    return em.merge(comment);
  }

  @Override
  public void delete(Comment comment) {
    em.remove(comment);
  }

  @Override
  public Comment findById(Long id) {
    return em.find(Comment.class, id);
  }
}
