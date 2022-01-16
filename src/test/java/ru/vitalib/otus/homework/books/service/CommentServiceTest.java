package ru.vitalib.otus.homework.books.service;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.vitalib.otus.homework.books.converter.BookConverter;
import ru.vitalib.otus.homework.books.dao.BookDao;
import ru.vitalib.otus.homework.books.domain.Book;
import ru.vitalib.otus.homework.books.domain.Comment;

@SpringBootTest(classes = {CommentServiceImpl.class})
@DisplayName("Comments test")
class CommentServiceTest {

  @Autowired
  CommentServiceImpl commentService;

  @MockBean
  BookDao bookDao;

  @MockBean
  BookConverter bookConverter;


  @Test
  void getCommentsForBook() {
    when(bookDao.findById(1L)).thenReturn(getBook());
    when(bookConverter.convertComments(any())).thenReturn(List.of());

    commentService.getCommentsForBook(1L);

    verify(bookDao).findById(1L);
    verify(bookConverter).convertComments(any());
  }

  private Optional<Book> getBook() {
    Book book = new Book();
    book.setComments(List.of(new Comment()));
    return Optional.of(book);
  }
}