package tudonoob.brokerschedule.domain;

import tudonoob.brokerschedule.domain.Broker;

public class Schedule {


    private Broker morning;

    private Broker afternoon;

    public Schedule(Broker morning, Broker afternoon) {
        this.morning = morning;
        this.afternoon = afternoon;
    }


    public Broker getMorning() {
        return morning;
    }

    public Broker getAfternoon() {
        return afternoon;
    }
}
