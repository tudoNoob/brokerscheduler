package tudonoob.brokerschedule.cache;

public class BrokerScheduleException extends RuntimeException {

    protected String exceptionName;

    public BrokerScheduleException(String message) {
        super(message);
    }

    public String getExceptionName() {
        return exceptionName;
    }
}
