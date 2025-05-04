package in.rahulojha.bookmanagerapi.validators.impl;

import com.sun.jdi.request.DuplicateRequestException;
import in.rahulojha.bookmanagerapi.entity.Book;
import in.rahulojha.bookmanagerapi.model.ValidationResponse;
import in.rahulojha.bookmanagerapi.repository.BookRepository;
import in.rahulojha.bookmanagerapi.validators.FieldValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class IsbnValidator implements FieldValidator {
    private final BookRepository bookRepository;

    public static String fieldName = "Book.ISBN";


    @Override
    public ValidationResponse validate(Book book) {

        if (book.getIsbn() == null) {
            return ValidationResponse.newFailureResponse(fieldName, "Can not be null or empty");
        }

        List<Book> books = bookRepository.findBookByIsbn(book.getIsbn());
        if (!books.isEmpty()) {
            throw new DuplicateRequestException("Duplicate ISBN. This ISBN is already present is Database");
//            return ValidationResponse.newFailureResponse(fieldName, "Duplicate ISBN. This ISBN is already present is Database");
        }
        return ValidationResponse.newSuccessResponse(fieldName, "Success");
    }
}
