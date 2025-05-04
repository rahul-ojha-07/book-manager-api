package in.rahulojha.bookmanagerapi.validators;

import in.rahulojha.bookmanagerapi.entity.Book;
import in.rahulojha.bookmanagerapi.model.ValidationResponse;

public interface FieldValidator {
    ValidationResponse validate(Book book);
}
