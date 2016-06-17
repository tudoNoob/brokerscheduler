package tudonoob.brokerschedule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tudonoob.brokerschedule.cache.BrokerCache;
import tudonoob.brokerschedule.domain.Broker;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class BrokerService {

    private BrokerCache cache;

    @Autowired
    public BrokerService(BrokerCache cache) {
        this.cache = cache;
    }

    public List<Object> filterBrokersByConstraint(String constraint) {
        Predicate<Object> matchBrokerByDayName = (broker) -> ((Broker) broker).getConstrains()
                .stream().anyMatch(day -> day.getDayName().equals(constraint));

        List<Object> brokersWhichHasTheSameConstrain = cache.getAllBrokers().values()
                .stream().filter(matchBrokerByDayName).collect(Collectors.toList());

        return brokersWhichHasTheSameConstrain;
    }

    public List<Object> filterBrokersByName(String name) {

        Predicate<Object> matchBrokerByName = (broker) -> ((Broker) broker).getName().contains(name);

        List<Object> brokersFilterByName = cache.getAllBrokers()
                .values().stream()
                .filter(matchBrokerByName)
                .collect(Collectors.toList());

        return brokersFilterByName;
    }


}
