package sk.stuba.fei.uim.oop.assignment3.list.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.list.logic.ILendingListService;
import sk.stuba.fei.uim.oop.assignment3.list.web.bodies.BookIdRequest;
import sk.stuba.fei.uim.oop.assignment3.list.web.bodies.LendingListResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/list")
public class LendingListController {

    @Autowired
    private ILendingListService lendingListService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LendingListResponse> addLendingList() {
        return new ResponseEntity<>(new LendingListResponse(this.lendingListService.create()), HttpStatus.CREATED);
    }

    @PostMapping(value = "/{id}/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public LendingListResponse addBookToLendingList(@PathVariable("id") Long lendingListId, @RequestBody BookIdRequest body) throws NotFoundException, IllegalOperationException {
        return new LendingListResponse(this.lendingListService.addToLendingList(lendingListId, body));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LendingListResponse> getAllLendingList() {
        return this.lendingListService.getAll().stream().map(LendingListResponse::new).collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public LendingListResponse getLendingList(@PathVariable("id") Long lendingListId) throws NotFoundException {
        return new LendingListResponse(this.lendingListService.getById(lendingListId));
    }

    @GetMapping(value = "/{id}/lend", produces = MediaType.TEXT_PLAIN_VALUE)
    public void lendLendingList(@PathVariable("id") Long lendingListId) throws NotFoundException, IllegalOperationException {
        this.lendingListService.lendLendingList(lendingListId);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteLendingList(@PathVariable("id") long lendingListId) throws NotFoundException {
        this.lendingListService.delete(lendingListId);
    }

    @DeleteMapping(value = "/{id}/remove")
    public void removeBookFromLendingList(@PathVariable("id") long lendingListId, @RequestBody BookIdRequest body) throws NotFoundException {
        this.lendingListService.removeBook(lendingListId,body);
    }


}
