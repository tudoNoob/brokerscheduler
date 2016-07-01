package tudonoob.brokerschedule.adapter;

import lombok.NoArgsConstructor;
import tudonoob.brokerschedule.domainmodel.Day;
import tudonoob.brokerschedule.domainmodel.DayName;
import tudonoob.brokerschedule.domainmodel.Shift;
import tudonoob.brokerschedule.model.DayModel;

@NoArgsConstructor
public class DayAdapter {


    public Day adaptDayModelToDay(DayModel model) {
        try {
            return new Day(new DayName(model.getDayName()), new Shift(model.getIsAvailableMorning()),
                    new Shift(model.getIsAvailableAfternoon()),
                    new Shift(model.getIsAvailableInTheWholeDay()));
        } catch (RuntimeException exception) {
            throw new IllegalArgumentException(exception);
        }
    }
}
