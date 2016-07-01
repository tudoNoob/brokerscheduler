package tudonoob.brokerschedule.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Schedule {

    private BrokerModel morning;

    private BrokerModel afternoon;

}
