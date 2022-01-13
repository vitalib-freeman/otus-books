package ru.vitalib.otus.homework.books.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vitalib.otus.homework.books.converter.BookConverter;
import ru.vitalib.otus.homework.books.dao.BookDao;
import ru.vitalib.otus.homework.books.dto.CommentDto;
import ru.vitalib.otus.homework.books.exception.NotFoundException;

@Service
public class CommentService {

  @Autowired
  BookDao bookDao;

  @Autowired
  BookConverter bookConverter;

  @Transactional
  public List<CommentDto> getCommentsForBook(Long bookId) {
    return Optional.ofNullable(bookDao.findById(bookId))
        .map(book -> bookConverter.convertComments(book.getComments()))
        .orElseThrow(NotFoundException::new);
  }
}
