package tudonoob.brokerschedule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tudonoob.brokerschedule.cache.BrokerCache;
import tudonoob.brokerschedule.domain.Schedule;
import tudonoob.brokerschedule.domain.WeekDay;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class BrokerSchedulerService {

    private ConcurrentHashMap<String, Object> cache;

    private ConcurrentHashMap<String, Schedule> scheduleMap;

    @Autowired
    public BrokerSchedulerService(BrokerCache cache) {
        this.cache = cache.cloneCache();
        this.scheduleMap = buildScheduleMap();
    }


    private ConcurrentHashMap<String, Schedule> buildScheduleMap() {
        ConcurrentHashMap<String, Schedule> scheduleMap = new ConcurrentHashMap<>();
        for (WeekDay day : WeekDay.values()) {
            scheduleMap.put(day.getWeekDayName(), new Schedule(null, null));
        }

        return scheduleMap;
    }


}
