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
        return new ErrorMessage(exception.getMessage(), exception.getExceptionName(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
