package tudonoob.brokerschedule.adapter;

import com.google.common.base.Preconditions;
import tudonoob.brokerschedule.domainmodel.Day;
import tudonoob.brokerschedule.domainmodel.WeekDays;
import tudonoob.brokerschedule.model.DayModel;

import java.util.ArrayList;
import java.util.List;

public class WeekDaysAdapter {


    private final DayAdapter dayAdapter;
    private final List<Day> days;

    public WeekDaysAdapter() {
        dayAdapter = new DayAdapter();
        days = new ArrayList<>();
    }

    public WeekDays adaptDaysListToWeekDaysList(List<DayModel> models) {
        try {
            models.stream().forEach(day -> {
                Day weekDay = dayAdapter.adaptDayModelToDay(day);
                days.add(weekDay);
            });
            return new WeekDays(days);
        } catch (RuntimeException exception) {
            throw new IllegalArgumentException(exception);
        }
    }
}
