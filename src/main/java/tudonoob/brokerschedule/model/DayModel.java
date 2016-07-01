package tudonoob.brokerschedule.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DayModel {

    private String dayName;

    private Boolean isAvailableAfternoon = false;

    private Boolean isAvailableMorning = false;

    private Boolean isAvailableInTheWholeDay = false;


}
