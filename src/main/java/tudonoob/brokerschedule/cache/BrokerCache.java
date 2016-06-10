package tudonoob.brokerschedule.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import tudonoob.brokerschedule.domain.Broker;

import java.util.*;
import java.util.concurrent.ConcurrentMap;

@Component
public class BrokerCache {

    public static final String BROKER_ALREADY_EXIST = "Broker Already Exist.";
    @Autowired
    @Qualifier("brokerCache")
    private Map<String, Object> cacheWrapper;

    public Broker addToCache(Broker broker) {
        ConcurrentMap<String, Object> cache = getConcurrentMap();

        if (existsBroker(broker, cache)) {
            throw new BrokerExistException(BROKER_ALREADY_EXIST);
        }

        String newIdForNewBroker = getIdForNewBroker(broker, cache);

        cache.put(newIdForNewBroker, broker);

        return getBroker(newIdForNewBroker);
    }

    private boolean existsBroker(Broker broker, ConcurrentMap<String, Object> cache) {
        if (cache.size() == 0) {
            return false;
        }

        return cache.containsValue(broker);
    }


    private String getIdForNewBroker(Broker broker, ConcurrentMap<String, Object> cache) {
        if (cache.size() == 0) {
            return "1";
        }

        List<String> idsList = getListOrderedWithSetOfString(cache);

        String newIdForNewBroker = buildIdNumber(idsList);

        return newIdForNewBroker;
    }

    private String buildIdNumber(List<String> idsList) {
        int actualSizeOfArray = idsList.size() - 1;
        String id = idsList.get(actualSizeOfArray);
        int idAsInteger = Integer.parseInt(id);
        return new StringBuilder().append(idAsInteger + 1).toString();
    }

    private List<String> getListOrderedWithSetOfString(ConcurrentMap<String, Object> cache) {
        Set<String> ids = cache.keySet();
        List<String> idsList = new ArrayList<>(ids);
        Collections.sort(idsList, new Comparator<String>() {
            @Override
            public int compare(String first, String second) {
                return first.compareTo(second);
            }
        });
        return idsList;
    }

    private ConcurrentMap<String, Object> getConcurrentMap() {
        return (ConcurrentMap<String, Object>) cacheWrapper.get("buildCache");
    }

    public Broker getBroker(String id) {
        return (Broker) getConcurrentMap().get(id);
    }


}
