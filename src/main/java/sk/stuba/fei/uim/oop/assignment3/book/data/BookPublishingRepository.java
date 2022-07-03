package sk.stuba.fei.uim.oop.assignment3.book.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookPublishingRepository extends JpaRepository<BookPublishing, Long> {
}
