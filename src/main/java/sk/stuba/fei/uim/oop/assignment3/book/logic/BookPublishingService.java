package sk.stuba.fei.uim.oop.assignment3.book.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.book.data.BookPublishing;
import sk.stuba.fei.uim.oop.assignment3.book.data.BookPublishingRepository;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookRequest;

@Service
public class BookPublishingService implements IBookPublishingService{

    @Autowired
    private BookPublishingRepository bookPublishingRepository;

    @Override
    public BookPublishing create(BookRequest body) {
        return this.bookPublishingRepository.save(new BookPublishing(body));
    }

    @Override
    public void save(BookPublishing bookPublishing) {
        this.bookPublishingRepository.save(bookPublishing);
    }
}
