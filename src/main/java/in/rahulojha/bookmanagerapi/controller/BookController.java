package in.rahulojha.bookmanagerapi.controller;

import in.rahulojha.bookmanagerapi.entity.Book;
import in.rahulojha.bookmanagerapi.model.BookModel;
import in.rahulojha.bookmanagerapi.service.BookService;
import in.rahulojha.bookmanagerapi.validators.FieldValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/books")
    ResponseEntity<List<BookModel>> getAllBooks() {
        return ResponseEntity.ok().body(bookService.getAllBooks());
    }

    @PostMapping("/books")
    ResponseEntity<BookModel> addBook(@RequestBody BookModel book) {

        return ResponseEntity.ok().body(bookService.addBook(book));
    }

}
