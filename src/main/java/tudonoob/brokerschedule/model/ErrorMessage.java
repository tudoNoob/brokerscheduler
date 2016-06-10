package tudonoob.brokerschedule.model;

import org.springframework.http.HttpStatus;

public class ErrorMessage {

    private String errorMessage;
    private HttpStatus status;


    public ErrorMessage(String errorMessage, HttpStatus status) {
        this.errorMessage = errorMessage;
        this.status = status;
    }


    public String getErrorMessage() {
        return errorMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
