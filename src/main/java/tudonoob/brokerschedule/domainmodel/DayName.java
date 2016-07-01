package tudonoob.brokerschedule.domainmodel;

import lombok.Getter;

import static tudonoob.brokerschedule.model.WeekDay.isAValidWeekDay;

@Getter
public class DayName {

    private String dayName;

    public DayName(String dayName) {
        isAValidWeekDay(dayName);
        this.dayName = dayName;
    }
}
