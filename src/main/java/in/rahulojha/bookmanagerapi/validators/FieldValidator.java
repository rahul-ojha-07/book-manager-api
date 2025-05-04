package in.rahulojha.bookmanagerapi.validators;

import in.rahulojha.bookmanagerapi.model.BookModel;
import in.rahulojha.bookmanagerapi.model.ValidationResponse;

public interface FieldValidator {
    ValidationResponse validate(BookModel book);
}
