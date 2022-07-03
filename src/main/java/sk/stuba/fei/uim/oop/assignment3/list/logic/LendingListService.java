package sk.stuba.fei.uim.oop.assignment3.list.logic;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.data.BookPublishing;
import sk.stuba.fei.uim.oop.assignment3.book.logic.IBookPublishingService;
import sk.stuba.fei.uim.oop.assignment3.book.logic.IBookService;
import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.list.data.LendingList;
import sk.stuba.fei.uim.oop.assignment3.list.data.LendingListRepository;
import sk.stuba.fei.uim.oop.assignment3.list.web.bodies.BookIdRequest;

import java.util.List;

@Service
public class LendingListService implements ILendingListService {

    @Autowired
    private LendingListRepository lendingListRepository;

    @Autowired
    private IBookService bookService;

    @Autowired
    private IBookPublishingService bookPublishingService;

    @Override
    public LendingList create() {
        return this.lendingListRepository.save(new LendingList());
    }
    @Override
    public LendingList addToLendingList(Long lendingListId, BookIdRequest body) throws NotFoundException, IllegalOperationException {
        LendingList lendingList = this.getUnlended(lendingListId);

        Book book = this.bookService.getBookById(body.getId());
        if(body.getId() == null){
            return lendingList;
        }
        if(book == null){
            throw new NotFoundException();
        }
        if(lendingList.getLent()){
            throw new IllegalOperationException();
        }
        BookPublishing bookPublishing = book.getBookPublishing();
        if( bookPublishing.getAmount().equals(bookPublishing.getLendCount())){
            throw new IllegalOperationException();
        }

        for (Book bookInLendingList : lendingList.getBooks()) {
            if (bookInLendingList.equals(book)){
                throw new IllegalOperationException();
            }
        }
        lendingList.addBook(book);

        return this.lendingListRepository.save(lendingList);
    }

    private LendingList getUnlended(Long id) throws NotFoundException, IllegalOperationException {
        LendingList lendingList = this.getById(id);
        if (lendingList.getLent()) {
            throw new IllegalOperationException();
        }
        return lendingList;
    }

    @Override
    public LendingList getById(long id) throws NotFoundException {
        LendingList lendingList = this.lendingListRepository.findLendingListById(id);
        if (lendingList == null) {
            throw new NotFoundException();
        }
        return lendingList;
    }

    @Override
    public List<LendingList> getAll() {
        return this.lendingListRepository.findAll();
    }

    @Override
    public void lendLendingList(Long lendingListId) throws NotFoundException, IllegalOperationException {
        LendingList lendingList = this.getUnlended(lendingListId);
        lendingList.setLent(true);
        for (Book book : lendingList.getBooks()) {
            BookPublishing bookPublishing = book.getBookPublishing();
            bookPublishing.increaseLendCount();
            this.bookPublishingService.save(bookPublishing);
        }
        this.lendingListRepository.save(lendingList);
    }

    @Override
    public void delete(long lendingListId) throws NotFoundException {
        LendingList lendingList = this.getById(lendingListId);
        for (Book book : lendingList.getBooks()) {
            BookPublishing bookPublishing = book.getBookPublishing();
            bookPublishing.decreaseLendCount();
            this.bookPublishingService.save(bookPublishing);
        }
        this.lendingListRepository.delete(this.getById(lendingListId));
    }

    @Override
    public void removeBook(long lendingListId, BookIdRequest body) throws NotFoundException {
        LendingList lendingList = this.getById(lendingListId);
        Book book = this.bookService.getBookById(body.getId());
        if (book == null){
            throw new NotFoundException();
        }
        lendingList.getBooks().remove(book);
        this.lendingListRepository.save(lendingList);
    }
}
