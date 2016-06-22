package tudonoob.brokerschedule.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Day {

    private String dayName;

    private Boolean isAvailableAfternoon = false;

    private Boolean isAvailableMorning = false;

    private Boolean isAvailableInTheWholeDay = false;


}
