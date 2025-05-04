package in.rahulojha.bookmanagerapi.repository;


import in.rahulojha.bookmanagerapi.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findBookByIsbn(Long isbn);
}
