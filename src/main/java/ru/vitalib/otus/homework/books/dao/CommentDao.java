package ru.vitalib.otus.homework.books.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vitalib.otus.homework.books.domain.Comment;

public interface CommentDao extends JpaRepository<Comment, Long> {
}
