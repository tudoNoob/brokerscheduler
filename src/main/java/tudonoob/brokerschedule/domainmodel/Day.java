package tudonoob.brokerschedule.domainmodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Day {

    private DayName dayName;

    private Shift isAvailableMorning;

    private Shift isAvailableAfternoon;

    private Shift isAvailableTheWholeDay;

}
