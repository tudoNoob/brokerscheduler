package tudonoob.brokerschedule.domain;

public enum WeekDay {

    MONDAY("MONDAY"),
    TUESDAY("TUESDAY"),
    WEDNESDAY("WEDNESDAY"),
    THURSDAY("THURSDAY"),
    FRIDAY("FRIDAY"),
    SATURDAY("SATURDAY"),
    SUNDAY("SUNDAY");

    private String weekDayName;

    WeekDay(String weekDayName) {
        this.weekDayName = weekDayName;
    }

    public String getWeekDayName() {
        return weekDayName;
    }
}
