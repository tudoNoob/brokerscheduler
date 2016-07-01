package tudonoob.brokerschedule.domainmodel;

import com.google.common.base.Preconditions;
import tudonoob.brokerschedule.model.DayModel;

import java.util.List;

public class WeekDays {

    private List<Day> days;

    public WeekDays(List<Day> days) {
        validateDays(days);
        this.days = days;
    }

    private void validateDays(List<Day> days) {
        Preconditions.checkNotNull(days);
        Preconditions.checkArgument(days.size() <= 7);
    }
}
