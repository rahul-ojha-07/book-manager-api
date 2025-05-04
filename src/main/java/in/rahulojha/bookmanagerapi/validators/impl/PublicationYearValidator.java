package in.rahulojha.bookmanagerapi.validators.impl;

import in.rahulojha.bookmanagerapi.model.BookModel;
import in.rahulojha.bookmanagerapi.model.ValidationResponse;
import in.rahulojha.bookmanagerapi.validators.FieldValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;

@Component
public class PublicationYearValidator implements FieldValidator {
    public static final String fieldName = "Book.publicationYear";

    @Override
    public ValidationResponse validate(BookModel book) {

        if (StringUtils.isEmpty(book.getPublicationYear())) {
            return ValidationResponse.newFailureResponse(fieldName, "Can not be Null or Empty.");
        }

        try {
            int publicationYear = Integer.parseInt(book.getPublicationYear());
            int currentYear = Instant.now().atZone(ZoneId.systemDefault()).getYear();
            if (currentYear < publicationYear) {
                return ValidationResponse.newFailureResponse(fieldName, "The year is in future");
            }
        } catch (NumberFormatException exception) {
            return ValidationResponse.newFailureResponse(fieldName, exception.getMessage());
        }

        return ValidationResponse.newSuccessResponse(fieldName, "Success");
    }
}
