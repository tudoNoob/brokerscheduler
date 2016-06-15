package tudonoob.brokerschedule.cache;

public class CacheEmptyException extends BrokerScheduleException {

    public CacheEmptyException(String message) {
        super(message);
        this.exceptionName = this.getClass().getName();
    }
}
