package tudonoob.brokerschedule;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BrokerRegistrationStep extends BrokerScheduleApplicationTests {

    @Autowired
    private BrokerController controller;

    private Broker broker;

    private List<Day> constrains;

    public BrokerRegistrationStep() {
        this.broker = new Broker();
        constrains = new ArrayList<>();
        broker.setConstrains(constrains);
    }

    @Given("^a broker with this name \"([^\"]*)\"$")
    public void i_have_broker_with_name(String brokerName) throws Throwable {
        broker.setName(brokerName);
    }

    @When("^this broker does not want to work on \"([^\"]*)\" in the shift \"(morning|afternoon|not\\savailable)\"$")
    public void _he_does_not_want_to_work_on(String dayName, String shift) {
        Day day = new Day();
        day.setDayName(dayName);

        if (shift.equals("afternoon")) {
            day.setNotAvailableAfternoon(true);
        } else if (shift.equals("morning")) {
            day.setNotAvailableMorning(true);
        }

        boolean isNotAvailableInTheWholeDay = (day.isNotAvailableAfternoon() || day.isNotAvailableMorning()) ?
                false : true;

        day.setNotAvailableInTheWholeDay(isNotAvailableInTheWholeDay);
        constrains.add(day);
    }

    @Then("^the broker should be registered with this name (\\w+)$")
    public void my_belly_should_growl(String expectedBrokerName) throws Throwable {
        Broker broker = controller.registerBroker(this.broker);
        System.out.println(broker);
        assertEquals(broker.getName(), expectedBrokerName);
    }

}
