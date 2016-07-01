package tudonoob.brokerschedule;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import tudonoob.brokerschedule.controller.BrokerScheduleController;
import tudonoob.brokerschedule.model.Schedule;

import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertEquals;

public class BrokerScheduleStep {


    @Autowired
    private BrokerScheduleController controller;

    private ConcurrentHashMap<String, Schedule> scheduleMap;

    @When("I schedule")
    public void i_schedule() {
        scheduleMap = controller.scheduleTheBrokersFromAWeek();
    }


    @Then("should schedule \"([^\"]*)\" on \"([^\"]*)\" by \"([^\"]*)\"")
    public void should_schedule_on_by(String brokerName, String day, String shift) {
        Schedule schedule = scheduleMap.get(day);
        String actual = "";
        if (shift.equals("morning")) {
            actual = schedule.getMorning().getName();
        } else {
            actual = schedule.getAfternoon().getName();
        }
        assertEquals(actual, brokerName);
    }


}
