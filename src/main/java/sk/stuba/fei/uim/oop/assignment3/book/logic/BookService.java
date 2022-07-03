package sk.stuba.fei.uim.oop.assignment3.book.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.author.data.Author;
import sk.stuba.fei.uim.oop.assignment3.author.logic.IAuthorService;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.data.BookPublishing;
import sk.stuba.fei.uim.oop.assignment3.book.data.BookRepository;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookUpdateRequest;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

import java.util.List;

@Service
public class BookService implements IBookService{

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private IAuthorService authorService;

    @Autowired
    private IBookPublishingService bookPublishingService;

    @Override
    public Book create(BookRequest request, BookPublishing bookPublishing) throws NotFoundException {
        Author author = this.authorService.getAuthorById(request.getAuthor());
        if(author == null){
            throw new NotFoundException();
        }
        Book book = this.bookRepository.save(new Book(request, bookPublishing, author));
        author.addBook(book);
        this.authorService.save(author);

        return book;
    }

    @Override
    public long addAmount(Long bookId, long increment) throws NotFoundException {
        Book book = this.getById(bookId);
        BookPublishing bookPublishing = book.getBookPublishing();
        bookPublishing.setAmount(bookPublishing.getAmount() + increment);
        this.bookPublishingService.save(bookPublishing);
        return bookPublishing.getAmount();
    }

    @Override
    public List<Book> getAll() {
        return this.bookRepository.findAll();
    }

    public Book getById(Long bookId) throws NotFoundException {
        Book book = this.bookRepository.findBookById(bookId);
        if (book == null){
            throw new NotFoundException();
        }
        return book;
    }

    @Override
    public long getAmount(Long bookId) throws NotFoundException {
        return this.getById(bookId).getBookPublishing().getAmount();
    }

    @Override
    public long getLendCount(Long bookId) throws NotFoundException {
        return this.getById(bookId).getBookPublishing().getLendCount();
    }

    @Override
    public Book update(Long bookId, BookUpdateRequest body) throws NotFoundException {
        Book book = this.getById(bookId);
        if (body.getName() != null) {
            book.setName(body.getName());
        }
        if (body.getDescription() != null) {
            book.setDescription(body.getDescription());
        }
        if (body.getAuthor() != null && body.getAuthor() != 0) {

            Author author =this.authorService.getAuthorById(body.getAuthor());
            if(author != null){
                book.setAuthor(author);
            }
        }
        if (body.getPages() != null && body.getPages() != 0) {
            book.setPages(body.getPages());
        }
        return this.bookRepository.save(book);
    }

    @Override
    public void delete(Long bookId) throws NotFoundException {
        Book book = this.getById(bookId);
        Author author = book.getAuthor();
        author.getBooks().remove(book);
        this.authorService.save(author);
        this.bookRepository.delete(book);
    }

    @Override
    public Book getBookById(Long id) {
        return this.bookRepository.findBookById(id);
    }


}
