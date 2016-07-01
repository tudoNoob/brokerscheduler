package tudonoob.brokerschedule.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Schedule {

    private Broker morning;

    private Broker afternoon;

}
