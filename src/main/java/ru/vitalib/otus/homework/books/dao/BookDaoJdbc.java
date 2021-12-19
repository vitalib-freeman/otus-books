package ru.vitalib.otus.homework.books.dao;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import ru.vitalib.otus.homework.books.domain.Book;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class BookDaoJdbc implements BookDao {
  private final NamedParameterJdbcOperations jdbc;

  @Override
  public void save(Book book) {
    jdbc.update(
        "insert into book (id, name) values(:id, :name)",
        Map.of("id", book.getId(), "name", book.getName())
    );
  }

  @Override
  public Book findById(long id) {
    return jdbc.queryForObject(
        "select id, name from book where id =:id",
        Map.of("id", id),
        (rs, rowNum) -> new Book(rs.getLong("id"), rs.getString("name"), null, null)
    );
  }

  @Override
  public void delete(long id) {
    jdbc.update("delete from book where id = :id", Map.of("id", id));

  }

  @Override
  public void update(long id, Book book) {
    jdbc.update(
        "update book set name = :name where id = :id",
        Map.of("name", book.getName(), "id", book.getId())
    );
  }

  @Override
  @SuppressWarnings("all")
  public int count() {
    return jdbc.queryForObject("select count(1) from book", Map.of(), Integer.class);
  }

  @Override
  public List<Book> findAll() {
    return jdbc.query(
        "select id, name from book",
        (rs, rowNum) -> new Book(rs.getLong("id"), rs.getString("name"), null, null)
    );
  }
}
