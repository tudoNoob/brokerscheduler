package tudonoob.brokerschedule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tudonoob.brokerschedule.cache.BrokerCache;
import tudonoob.brokerschedule.domain.Broker;
import tudonoob.brokerschedule.domain.Day;
import tudonoob.brokerschedule.domain.Schedule;
import tudonoob.brokerschedule.domain.WeekDay;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class BrokerSchedulerService {

    public static final String NO_BROKERS_MSG_ERROR = "Should had some brokers to be able to schedule for a week.";

    private ConcurrentHashMap<String, Object> cacheClone;

    private BrokerCache cache;
    
    private BrokerService brokerService;

    private ConcurrentHashMap<String, Schedule> scheduleMap;


    @Autowired
    public BrokerSchedulerService(BrokerCache cache, BrokerService brokerService) {
        this.cache = cache;
        this.brokerService = brokerService;
        this.scheduleMap = buildScheduleMap();
    }


    private ConcurrentHashMap<String, Schedule> buildScheduleMap() {
        ConcurrentHashMap<String, Schedule> scheduleMap = new ConcurrentHashMap<>();
        for (WeekDay day : WeekDay.values()) {
            scheduleMap.put(day.getWeekDayName(), new Schedule(null, null));
        }

        return scheduleMap;
    }


    public ConcurrentHashMap<String, Schedule> scheduleBrokersForAWeek() {
        cacheClone = cache.cloneCache();
        if (cacheClone == null) {
            throw new NoBrokerException(NO_BROKERS_MSG_ERROR);
        }
        prioritizeThoseBrokersWhoHasOneConstraint();


        return scheduleMap;
    }

    private void prioritizeThoseBrokersWhoHasOneConstraint() {
        for (WeekDay day : WeekDay.values()) {
            List<Object> brokersPrioritized = brokerService.
                    filterBrokersByOnlyOneConstraint(day.getWeekDayName().toLowerCase());

            Schedule schedule = scheduleMap.get(day.getWeekDayName());

            addAListOfBrokersToSchedule(brokersPrioritized, schedule);

            removeBrokersFromCache(brokersPrioritized);
        }
    }

    private void addAListOfBrokersToSchedule(List<Object> brokersPrioritized, Schedule schedule) {
        brokersPrioritized.forEach(broker -> {
            Day constraint = ((Broker) broker).getConstrains().get(0);

            if (constraint.getIsAvailableMorning()) {
                schedule.setMorning((Broker) broker);
            } else if (constraint.getIsAvailableAfternoon()) {
                schedule.setAfternoon((Broker) broker);
            }


        });
    }

    private void removeBrokersFromCache(List<Object> brokersPrioritized) {
        brokersPrioritized.forEach(broker -> {
            cacheClone.keySet().forEach(key -> {
                if (cacheClone.get(key).equals(broker)) {
                    cacheClone.remove(key);
                }
            });
        });
    }


}
