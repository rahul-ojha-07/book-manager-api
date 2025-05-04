package in.rahulojha.bookmanagerapi.service;


import in.rahulojha.bookmanagerapi.entity.Book;
import in.rahulojha.bookmanagerapi.model.ValidationResponse;
import in.rahulojha.bookmanagerapi.repository.BookRepository;
import in.rahulojha.bookmanagerapi.validators.FieldValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repository;
    private final List<FieldValidator> fieldValidators;



    public Book addBook(Book book) {
        validateRequest(book);
        return repository.save(book);
    }




    private void validateRequest(Book book){
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
