package tudonoob.brokerschedule.service;

public class NoBrokerException extends RuntimeException {
    public NoBrokerException(String errorMessage) {
        super(errorMessage);
    }
}
