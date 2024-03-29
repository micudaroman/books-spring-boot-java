package sk.stuba.fei.uim.oop.assignment3.author.data;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.author.web.bodies.AuthorRequest;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    @OneToMany(mappedBy = "author",orphanRemoval = true)
    private List<Book> books;

    public Author() {
        this.books = new ArrayList<>();
    }

    public Author(AuthorRequest request) {
        this.name = request.getName();
        this.surname = request.getSurname();
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        this.books.add(book);
    }
}
