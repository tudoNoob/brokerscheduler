package tudonoob.brokerschedule.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tudonoob.brokerschedule.cache.BrokerCache;
import tudonoob.brokerschedule.model.Schedule;
import tudonoob.brokerschedule.model.WeekDay;

import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

public class BrokerSchedulerServiceTest {

    private BrokerSchedulerService service;

    @Mock
    private BrokerCache cache;


    private BrokerService brokerService;
    private ConcurrentHashMap<String, Object> brokersMocked;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        brokerService = new BrokerService(cache);
        brokersMocked = new BrokersBuilder().buildBrokersMockFromFile();
        when(cache.getAllBrokers()).thenReturn(brokersMocked);
        when(cache.cloneCache()).thenReturn(brokersMocked);
        service = new BrokerSchedulerService(cache, brokerService);
    }

    @Test(expected = NoBrokerException.class)
    public void shouldBeAbleToReturnAnEmptyScheduleGivenNoBrokersRegistered() throws Exception {
        when(cache.cloneCache()).thenReturn(null);
        service = new BrokerSchedulerService(cache, brokerService);
        service.scheduleBrokersForAWeek();
    }

    @Test
    public void shouldReturnOnlyOneBrokerInThursdayInTheMorning() throws Exception {
        ConcurrentHashMap<String, Schedule> scheduleMap = service.scheduleBrokersForAWeek();
        Schedule schedule = scheduleMap.get(WeekDay.THURSDAY.getWeekDayName());

        assertNotNull(schedule.getMorning());
    }

}