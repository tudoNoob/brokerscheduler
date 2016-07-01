package tudonoob.brokerschedule.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import tudonoob.brokerschedule.annotation.LogMethod;
import tudonoob.brokerschedule.model.BrokerModel;
import tudonoob.brokerschedule.model.DayModel;
import tudonoob.brokerschedule.model.WeekDay;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class BrokerCache {

    private static final String BROKER_ERROR = "BrokerModel already exist.";
    private static final String CONSTRAIN_ERROR = "Constrain with error.";
    private static final String CACHE_IS_EMPTY_ERROR = "Cache is Empty";
    private static final String FIRST_ID_NUMBER = "1";

    @Autowired
    @Qualifier("brokerCache")
    private Map<String, Object> cacheWrapper;

    @LogMethod(level = "INFO", message = "Executing add To Cache.")
    public BrokerModel addToCache(BrokerModel brokerModel) {
        ConcurrentMap<String, Object> cache = getConcurrentMap();

        validateBroker(brokerModel, cache);

        String newIdForNewBroker = getIdForNewBrokerAndValidateIfCacheIsEmpty(cache);

        addBrokerIntoCache(brokerModel, cache, newIdForNewBroker);

        return getBroker(newIdForNewBroker);
    }

    private void addBrokerIntoCache(BrokerModel brokerModel, ConcurrentMap<String, Object> cache, String newIdForNewBroker) {
        cache.put(newIdForNewBroker, brokerModel);
    }

    private void validateBroker(BrokerModel brokerModel, ConcurrentMap<String, Object> cache) {
        if (existsBroker(brokerModel, cache)) {
            throw new BrokerAddOperationException(BROKER_ERROR);
        } else if (!validateConstrains(brokerModel.getConstrains())) {
            throw new BrokerAddOperationException(CONSTRAIN_ERROR);
        }
    }

    public BrokerModel updateBroker(String id, BrokerModel brokerModel) {
        ConcurrentMap<String, Object> cache = getConcurrentMap();
        BrokerModel oldBrokerModel = getBroker(id);

        if (oldBrokerModel == null) {
            addBrokerIntoCache(brokerModel, cache, id);
        } else {
            cache.remove(id);
            addBrokerIntoCache(brokerModel, cache, id);
        }

        return brokerModel;
    }

    private boolean existsBroker(BrokerModel brokerModel, ConcurrentMap<String, Object> cache) {
        return cache.containsValue(brokerModel);
    }

    private String getIdForNewBrokerAndValidateIfCacheIsEmpty(ConcurrentMap<String, Object> cache) {

        if (cache.size() == 0) {
            return FIRST_ID_NUMBER;
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

        Comparator<String> comparatorByName = (String first, String second) -> first.compareTo(second);
        Collections.sort(idsList, comparatorByName);

        return idsList;
    }

    private ConcurrentMap<String, Object> getConcurrentMap() {
        return (ConcurrentMap<String, Object>) cacheWrapper.get("buildCache");
    }

    private BrokerModel getBroker(String id) {
        return (BrokerModel) getConcurrentMap().get(id);
    }

    private boolean validateConstrains(List<DayModel> constrains) {
        boolean isValidated = true;

        try {

            constrains.forEach((day) ->
                    WeekDay.valueOf(day.getDayName().toUpperCase())
            );

        } catch (IllegalArgumentException exception) {
            isValidated = false;
        }

        return isValidated;
    }

    public ConcurrentMap<String, Object> getAllBrokers() {
        ConcurrentMap<String, Object> cache = getConcurrentMap();

        if ((cache == null) || (cache.size() == 0)) {
            throw new CacheEmptyException(CACHE_IS_EMPTY_ERROR);
        }

        return cache;
    }

    public ConcurrentHashMap<String, Object> cloneCache() {
        ConcurrentMap<String, Object> brokers = this.getConcurrentMap();
        return new ConcurrentHashMap<>(brokers);
    }

    public void clear() {
        cacheWrapper = new ConcurrentHashMap<>();
        cacheWrapper.put("buildCache", new ConcurrentHashMap<String, Object>());
    }
}
