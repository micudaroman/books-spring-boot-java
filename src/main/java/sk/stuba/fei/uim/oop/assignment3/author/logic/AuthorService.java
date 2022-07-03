package sk.stuba.fei.uim.oop.assignment3.author.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.author.data.Author;
import sk.stuba.fei.uim.oop.assignment3.author.data.AuthorRepository;
import sk.stuba.fei.uim.oop.assignment3.author.web.bodies.AuthorRequest;
import sk.stuba.fei.uim.oop.assignment3.author.web.bodies.AuthorUpdateRequest;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

import java.util.List;

@Service
public class AuthorService implements IAuthorService{

    @Autowired
    private AuthorRepository authorRepository;
    @Override
    public List<Author> getAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Author create(AuthorRequest request) {
        return this.authorRepository.save(new Author(request));
    }

    @Override
    public Author getById(Long authorId) throws NotFoundException {
        Author author = this.authorRepository.findAuthorById(authorId);
        if (author == null) {
            throw new NotFoundException();
        }
        return author;
    }

    @Override
    public Author update(Long authorId, AuthorUpdateRequest body) throws NotFoundException {
        Author author = this.getById(authorId);
        if (body.getName() != null) {
            author.setName(body.getName());
        }
        if (body.getSurname() != null) {
            author.setSurname(body.getSurname());
        }
        return this.authorRepository.save(author);
    }

    @Override
    public void delete(Long authorId) throws NotFoundException {
        this.authorRepository.delete(this.getById(authorId));
    }

    @Override
    public Author getAuthorById(Long author) {
        return this.authorRepository.findAuthorById(author);
    }

    @Override
    public void save(Author author) {
        this.authorRepository.save(author);
    }
}