package tudonoob.brokerschedule.domain;

public class Day {

    private String dayName;

    private Boolean isAvailableAfternoon = false;

    private Boolean isAvailableMorning = false;

    private Boolean isAvailableInTheWholeDay = false;

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public Boolean getAvailableAfternoon() {
        return isAvailableAfternoon;
    }

    public void setAvailableAfternoon(Boolean availableAfternoon) {
        isAvailableAfternoon = availableAfternoon;
    }

    public Boolean getAvailableMorning() {
        return isAvailableMorning;
    }

    public void setAvailableMorning(Boolean availableMorning) {
        isAvailableMorning = availableMorning;
    }

    public Boolean getAvailableInTheWholeDay() {
        return isAvailableInTheWholeDay;
    }

    public void setAvailableInTheWholeDay(Boolean availableInTheWholeDay) {
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
