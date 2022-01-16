package ru.vitalib.otus.homework.books.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.vitalib.otus.homework.books.domain.Book;

import java.util.List;

public interface BookDao extends JpaRepository<Book, Long> {
  @Query("select b from Book b join fetch b.author join fetch b.genre")
  List<Book> findAll();
}
