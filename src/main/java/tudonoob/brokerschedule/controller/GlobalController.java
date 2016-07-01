package tudonoob.brokerschedule.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tudonoob.brokerschedule.cache.BrokerScheduleException;
import tudonoob.brokerschedule.model.ErrorMessage;

@ControllerAdvice
@RestController
public class GlobalController {

    @ExceptionHandler(BrokerScheduleException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleError(BrokerScheduleException exception) {
        return buildErrorMessage(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorMessage buildErrorMessage(Exception exception, HttpStatus status) {
        if (exception instanceof BrokerScheduleException) {
            return new ErrorMessage(exception.getMessage(), ((BrokerScheduleException) exception).getExceptionName(),
                    status);
        }
        return new ErrorMessage(exception.getMessage(), exception.getClass().getName(),
                status);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleDomainBuildError(IllegalAccessException exception) {
        return buildErrorMessage(exception, HttpStatus.BAD_REQUEST);
    }
}
