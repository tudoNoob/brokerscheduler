package tudonoob.brokerschedule.service;

import tudonoob.brokerschedule.cache.BrokerScheduleException;

public class NoBrokerException extends BrokerScheduleException{
    public NoBrokerException(String errorMessage) {
        super(errorMessage);
        this.exceptionName = this.getClass().getName();
    }
}
