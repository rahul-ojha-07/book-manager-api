package in.rahulojha.bookmanagerapi.controller;

import in.rahulojha.bookmanagerapi.model.BookModel;
import in.rahulojha.bookmanagerapi.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/books")
    ResponseEntity<List<BookModel>> getAllBooks() {
        return ResponseEntity.ok().body(bookService.getAllBooks());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/books")
    ResponseEntity<BookModel> addBook(@RequestBody BookModel book) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookService.addBook(book));
    }

    @GetMapping("/books/{id}")
    ResponseEntity<BookModel> getBookById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookService.getBookById(id));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/books/{id}")
    ResponseEntity<BookModel> updateBookById(@PathVariable("id") Long id, @RequestBody BookModel bookModel) {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookService.updateBookById(id, bookModel));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/books/{id}")
    ResponseEntity<String> deleteBookById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookService.deleteBookById(id));
    }
}
