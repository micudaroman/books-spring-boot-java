package sk.stuba.fei.uim.oop.assignment3.book.logic;

import sk.stuba.fei.uim.oop.assignment3.book.data.BookPublishing;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

public interface IBookPublishingService {
    BookPublishing create(BookRequest body) throws NotFoundException;

    void save(BookPublishing bookPublishing);
}
