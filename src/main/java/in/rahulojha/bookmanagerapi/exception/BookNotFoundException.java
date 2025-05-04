package in.rahulojha.bookmanagerapi.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class BookNotFoundException extends ResponseStatusException {
    public BookNotFoundException(HttpStatusCode status, String reason) {
        super(status, reason);
    }
}
