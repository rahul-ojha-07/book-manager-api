package in.rahulojha.bookmanagerapi.validators.impl;

import com.sun.jdi.request.DuplicateRequestException;
import in.rahulojha.bookmanagerapi.entity.Book;
import in.rahulojha.bookmanagerapi.model.BookModel;
import in.rahulojha.bookmanagerapi.model.ValidationResponse;
import in.rahulojha.bookmanagerapi.repository.BookRepository;
import in.rahulojha.bookmanagerapi.validators.FieldValidator;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class IsbnValidator implements FieldValidator {
    private final BookRepository bookRepository;

    public static final String fieldName = "Book.ISBN";


    @Override
    public ValidationResponse validate(BookModel book) {

        if (null == book.getIsbn()) {
            return ValidationResponse.newFailureResponse(fieldName, "Can not be Null or Empty.");
        }

        List<Book> books = bookRepository.findBookByIsbn(book.getIsbn());
        if (!books.isEmpty()) {
            throw new DuplicateRequestException("Duplicate ISBN. This ISBN is already present is Database");
        }
        return ValidationResponse.newSuccessResponse(fieldName, "Success");
    }
}
