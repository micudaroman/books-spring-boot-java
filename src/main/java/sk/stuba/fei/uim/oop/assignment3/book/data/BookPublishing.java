package sk.stuba.fei.uim.oop.assignment3.book.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookRequest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BookPublishing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long amount;
    private Long lendCount;

    public BookPublishing(BookRequest body){
        this.amount = body.getAmount();
        this.lendCount = body.getLendCount();
    }

    public void increaseLendCount() {
        this.lendCount++;
    }
    public void decreaseLendCount() {
        this.lendCount--;
    }
}
