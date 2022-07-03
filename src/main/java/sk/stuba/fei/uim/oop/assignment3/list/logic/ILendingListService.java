package sk.stuba.fei.uim.oop.assignment3.list.logic;


import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.list.data.LendingList;
import sk.stuba.fei.uim.oop.assignment3.list.web.bodies.BookIdRequest;

import java.util.List;

public interface ILendingListService {
    LendingList create();

    LendingList addToLendingList(Long lendingListId, BookIdRequest body) throws NotFoundException, IllegalOperationException;

    LendingList getById(long id) throws NotFoundException;

    List<LendingList> getAll();

    void lendLendingList(Long lendingListId) throws NotFoundException, IllegalOperationException;

    void delete(long lendingListId) throws NotFoundException;

    void removeBook(long lendingListId, BookIdRequest body) throws NotFoundException;
}
