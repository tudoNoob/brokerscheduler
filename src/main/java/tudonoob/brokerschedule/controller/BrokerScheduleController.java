package tudonoob.brokerschedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tudonoob.brokerschedule.domain.Schedule;
import tudonoob.brokerschedule.service.BrokerSchedulerService;

import java.util.concurrent.ConcurrentHashMap;

@RestController
public class BrokerScheduleController {


    @Autowired
    private BrokerSchedulerService service;

    @RequestMapping(value = "/scheduleBrokers", method = RequestMethod.GET)
    public ConcurrentHashMap<String, Schedule> scheduleTheBrokersFromAWeek() {
        return service.scheduleBrokersForAWeek();
    }

}
