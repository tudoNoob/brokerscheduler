package tudonoob.brokerschedule.domain;

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

    public void setMorning(Broker morning) {
        this.morning = morning;
    }

    public void setAfternoon(Broker afternoon) {
        this.afternoon = afternoon;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "morning=" + morning +
                ", afternoon=" + afternoon +
                '}';
    }
}
