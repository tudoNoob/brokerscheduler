package tudonoob.brokerschedule.cache;

public class BrokerAddOperationException extends BrokerScheduleException {
    public BrokerAddOperationException(String message) {
        super(message);
        this.exceptionName = this.getClass().getName();
    }
}
