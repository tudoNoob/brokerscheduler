package tudonoob.brokerschedule.domainmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Broker {

    private FullName name;

    private WeekDays days;


}
