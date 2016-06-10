package tudonoob.brokerschedule.domain;

public class Day {

    private String dayName;

    private boolean isAvailableAfternoon;

    private boolean isAvailableMorning;

    private boolean isAvailableInTheWholeDay;


    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public boolean isAvailableAfternoon() {
        return isAvailableAfternoon;
    }

    public void setAvailableAfternoon(boolean availableAfternoon) {
        isAvailableAfternoon = availableAfternoon;
    }

    public boolean isAvailableMorning() {
        return isAvailableMorning;
    }

    public void setAvailableMorning(boolean availableMorning) {
        isAvailableMorning = availableMorning;
    }

    public boolean isAvailableInTheWholeDay() {
        return isAvailableInTheWholeDay;
    }

    public void setAvailableInTheWholeDay(boolean availableInTheWholeDay) {
        isAvailableInTheWholeDay = availableInTheWholeDay;
    }

    @Override
    public String toString() {
        return "Day{" +
                "dayName='" + dayName + '\'' +
                ", isAvailableAfternoon=" + isAvailableAfternoon +
                ", isAvailableMorning=" + isAvailableMorning +
                ", isAvailableInTheWholeDay=" + isAvailableInTheWholeDay +
                '}';
    }
}
