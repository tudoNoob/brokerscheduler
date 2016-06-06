package tudonoob.brokerschedule;

public class Day {

    private String dayName;

    private boolean isNotAvailableAfternoon;

    private boolean isNotAvailableMorning;

    private boolean isNotAvailableInTheWholeDay;


    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public boolean isNotAvailableAfternoon() {
        return isNotAvailableAfternoon;
    }

    public void setNotAvailableAfternoon(boolean notAvailableAfternoon) {
        isNotAvailableAfternoon = notAvailableAfternoon;
    }

    public boolean isNotAvailableMorning() {
        return isNotAvailableMorning;
    }

    public void setNotAvailableMorning(boolean notAvailableMorning) {
        isNotAvailableMorning = notAvailableMorning;
    }

    public boolean isNotAvailableInTheWholeDay() {
        return isNotAvailableInTheWholeDay;
    }

    public void setNotAvailableInTheWholeDay(boolean notAvailableInTheWholeDay) {
        isNotAvailableInTheWholeDay = notAvailableInTheWholeDay;
    }

    @Override
    public String toString() {
        return "Day{" +
                "dayName='" + dayName + '\'' +
                ", isNotAvailableAfternoon=" + isNotAvailableAfternoon +
                ", isNotAvailableMorning=" + isNotAvailableMorning +
                ", isNotAvailableInTheWholeDay=" + isNotAvailableInTheWholeDay +
                '}';
    }
}
