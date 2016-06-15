package tudonoob.brokerschedule.model;

import org.springframework.http.HttpStatus;

public class ErrorMessage {

    private final String exceptionName;
    private final String errorMessage;
    private final HttpStatus status;


    public ErrorMessage(String errorMessage, String exceptionName, HttpStatus status) {
        this.errorMessage = errorMessage;
        this.exceptionName = exceptionName;
        this.status = status;
    }


    public String getErrorMessage() {
        return errorMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getExceptionName() {
        return exceptionName;
    }
}
