package in.rahulojha.bookmanagerapi.model;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidationResponse {

    private String fieldName;
    private String message;
    private StatusEnum status = StatusEnum.FAILURE;

    public static ValidationResponse newSuccessResponse(String fieldName, String message) {
        return new ValidationResponse(fieldName, message, StatusEnum.SUCCESS);
    }

    public static ValidationResponse newFailureResponse(String fieldName, String message) {
        return new ValidationResponse(fieldName, message, StatusEnum.FAILURE);
    }

    public boolean isFailure() {
        return StatusEnum.FAILURE.compareTo(this.status) == 0;
    }

    public enum StatusEnum {
        SUCCESS, FAILURE
    }
}
