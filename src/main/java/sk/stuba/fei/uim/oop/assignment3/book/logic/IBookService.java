package sk.stuba.fei.uim.oop.assignment3.book.logic;

import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.data.BookPublishing;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookUpdateRequest;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

import java.util.List;

public interface IBookService {
    Book create(BookRequest request, BookPublishing bookPublishing) throws NotFoundException;

    long addAmount(Long bookId, long amount) throws NotFoundException;

    List<Book> getAll();

    Book getById(Long bookId) throws NotFoundException;

    long getAmount(Long bookId) throws NotFoundException;

    long getLendCount(Long bookId) throws NotFoundException;

    Book update(Long bookId, BookUpdateRequest body) throws NotFoundException;

    void delete(Long bookId) throws NotFoundException;

    Book getBookById(Long id);
}
