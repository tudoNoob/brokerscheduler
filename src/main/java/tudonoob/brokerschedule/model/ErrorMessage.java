package tudonoob.brokerschedule.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class ErrorMessage {

    private final String exceptionName;
    private final String errorMessage;
    private final HttpStatus status;

}
