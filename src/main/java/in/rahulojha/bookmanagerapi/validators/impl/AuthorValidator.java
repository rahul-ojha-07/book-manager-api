package in.rahulojha.bookmanagerapi.validators.impl;

import in.rahulojha.bookmanagerapi.model.BookModel;
import in.rahulojha.bookmanagerapi.model.ValidationResponse;
import in.rahulojha.bookmanagerapi.validators.FieldValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class AuthorValidator implements FieldValidator {

    public static final String fieldName = "Book.author";


    @Override
    public ValidationResponse validate(BookModel book) {
        if (book != null && StringUtils.isEmpty(book.getAuthor())) {
            return ValidationResponse.newFailureResponse(fieldName, "Can not be Null or Empty.");
        }
        return ValidationResponse.newSuccessResponse(fieldName, "Success");
    }
}
