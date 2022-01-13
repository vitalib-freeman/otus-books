package ru.vitalib.otus.homework.books.dao;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.vitalib.otus.homework.books.domain.Book;
import ru.vitalib.otus.homework.books.domain.Comment;

@DataJpaTest
@Import(CommentDaoJPA.class)
class CommentDaoJPATest {

  private static final long EXISTING_COMMENT_ID = 1L;
  @Autowired
  TestEntityManager em;

  @Autowired
  CommentDaoJPA commentDaoJPA;

  @Test
  @DisplayName("Find comment by id")
  public void findCommentById() {
    Comment comment = commentDaoJPA.findById(EXISTING_COMMENT_ID);

    assertThat(comment).isNotNull();
  }


  @Test
  @DisplayName("Save comment")
  void save() {
    Comment comment = commentDaoJPA.save(new Comment(0L, "comment", null));

    assertThat(comment.getId()).isNotEqualTo(0L);
  }

  @Test
  void delete() {
    Comment comment = em.persist(new Comment(0L, "comment", null));

    commentDaoJPA.delete(comment);

    assertThat(em.find(Comment.class, comment.getId()))
        .isEqualTo(null);
  }

  @Test
  void findById() {
    Comment comment = em.persist(new Comment(0L, "comment", null));

    assertThat(commentDaoJPA.findById(comment.getId()))
        .isNotNull();
  }
}