package in.rahulojha.bookmanagerapi.service;


import in.rahulojha.bookmanagerapi.entity.Book;
import in.rahulojha.bookmanagerapi.exception.BookNotFoundException;
import in.rahulojha.bookmanagerapi.model.BookModel;
import in.rahulojha.bookmanagerapi.model.ValidationResponse;
import in.rahulojha.bookmanagerapi.repository.BookRepository;
import in.rahulojha.bookmanagerapi.validators.FieldValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repository;
    private final List<FieldValidator> fieldValidators;


    public List<BookModel> getAllBooks() {
        return repository.findAll().stream()
                .map(BookModel::new)
                .toList();
    }

    public BookModel addBook(BookModel book) {
        validateRequest(book);
        return BookModel.fromEntity(repository.save(book.toEntity()));
    }

    public BookModel getBookById(Long id) {
        Optional<Book> optionalBook = repository.findById(id);
        return optionalBook
                .map( BookModel::new)
                .orElseThrow(() ->
                        new BookNotFoundException(HttpStatus.NOT_FOUND, String.format("Book with given id: %s  not found", id)));
    }

    public BookModel updateBookById(Long id, BookModel bookToUpdate) {
        if (!repository.existsById(id)) {
            throw new BookNotFoundException(HttpStatus.NOT_FOUND, String.format("Book with given id: %s  not found", id));
        }
        bookToUpdate.setId(id);
        validateRequest(bookToUpdate);
        Book updatedBook = repository.save(bookToUpdate.toEntity());
        return  BookModel.fromEntity(updatedBook);
    }

    public String deleteBookById(Long id) {
        if (!repository.existsById(id)) {
            throw new BookNotFoundException(HttpStatus.NOT_FOUND, String.format("Book with given id: %s  not found", id));
        }
        repository.deleteById(id);
        return "Deleted! Book with Id: " + id;
    }


    private void validateRequest(BookModel book){

        if (null == book){
            throw new IllegalArgumentException("Book does not have any data.");
        }

         List<ValidationResponse> validationResponses = fieldValidators.stream()
                .map(validator -> validator.validate(book))
                .filter(ValidationResponse::isFailure).toList();


        String validationResponseMessage = validationResponses.stream()
                .map(response -> String.format("%s -> %s", response.getFieldName(), response.getMessage()))
                .collect(Collectors.joining(", "));

        if (!validationResponseMessage.isEmpty()) {
            throw new IllegalArgumentException(validationResponseMessage);
        }
    }

}
