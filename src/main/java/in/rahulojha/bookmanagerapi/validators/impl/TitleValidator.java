package in.rahulojha.bookmanagerapi.validators.impl;

import in.rahulojha.bookmanagerapi.model.BookModel;
import in.rahulojha.bookmanagerapi.model.ValidationResponse;
import in.rahulojha.bookmanagerapi.validators.FieldValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class TitleValidator implements FieldValidator {

    public static final String fieldName = "Book.title";

    @Override
    public ValidationResponse validate(BookModel book) {
        if (StringUtils.isEmpty(book.getTitle())) {
            return ValidationResponse.newFailureResponse(fieldName, "Can not be Null or Empty.");
        }
        return ValidationResponse.newSuccessResponse(fieldName, "Success");
    }
}
