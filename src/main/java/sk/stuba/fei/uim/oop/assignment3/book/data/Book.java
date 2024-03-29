package sk.stuba.fei.uim.oop.assignment3.book.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.author.data.Author;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookRequest;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Integer pages;
    @ManyToOne
    private Author author;
    @OneToOne
    private BookPublishing bookPublishing;


    public Book(BookRequest request, BookPublishing bookPublishing, Author author) {
        this.name = request.getName();
        this.description = request.getDescription();
        this.pages = request.getPages();
        this.author = author;
        this.bookPublishing = bookPublishing;

    }
}
