package tudonoob.brokerschedule.adapter;

import lombok.NoArgsConstructor;
import tudonoob.brokerschedule.domainmodel.Broker;
import tudonoob.brokerschedule.domainmodel.FullName;
import tudonoob.brokerschedule.model.BrokerModel;

@NoArgsConstructor
public class BrokerAdapter {

    public Broker adaptBroker(BrokerModel model) {
        try {
            return new Broker(new FullName(model.getName()),
                    new WeekDaysAdapter().adaptDaysListToWeekDaysList(model.getConstrains()));
        } catch (RuntimeException exception) {
            throw new IllegalArgumentException(exception);
        }
    }

}
