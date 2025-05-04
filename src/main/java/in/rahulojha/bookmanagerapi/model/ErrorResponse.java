package in.rahulojha.bookmanagerapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.time.Instant;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private HttpStatus httpStatus;
    private int statusCode;
    private Instant timestamp;
}
