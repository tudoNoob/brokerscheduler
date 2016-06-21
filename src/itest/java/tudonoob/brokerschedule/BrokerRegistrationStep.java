package tudonoob.brokerschedule;

import cucumber.api.java.en.*;
import org.springframework.beans.factory.annotation.Autowired;
import tudonoob.brokerschedule.controller.BrokerController;
import tudonoob.brokerschedule.domain.Broker;
import tudonoob.brokerschedule.domain.Day;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class BrokerRegistrationStep {

    @Autowired
    private BrokerController controller;


    private Broker broker;

    private List<Day> constrains;

    private RuntimeException exception;

    @Given("^a broker with this name \"([^\"]*)\"$")
    public void i_have_broker_with_name(String brokerName) throws Throwable {
        this.broker = new Broker();
        constrains = new ArrayList<>();
        broker.setConstrains(constrains);
        broker.setName(brokerName);
    }

    @And("^this broker does not want to work on \"([^\"]*)\" in the shift \"(morning|afternoon|not\\savailable)\"$")
    public void _he_does_not_want_to_work_on(String dayName, String shift) {
        Day day = new Day();
        day.setDayName(dayName);

        if (shift.equals("afternoon")) {
            day.setAvailableAfternoon(true);
        } else if (shift.equals("morning")) {
            day.setAvailableMorning(true);
        }

        boolean isNotAvailableInTheWholeDay = (day.getAvailableAfternoon() || day.getAvailableMorning()) ?
                false : true;

        day.setAvailableInTheWholeDay(isNotAvailableInTheWholeDay);
        constrains.add(day);
    }

    @When("^register the broker")
    public void register() {
        try {
            controller.registerBroker(this.broker);
        } catch (RuntimeException exception) {
            this.exception = exception;
        }
    }

    @Then("^the broker should be registered with this name (\\w+)$")
    public void my_belly_should_growl(String expectedBrokerName) throws Throwable {
        assertNull(exception);
        assertEquals(broker.getName(), expectedBrokerName);
    }


    @Then("^as the broker is already register should throw runtimeException")
    public void brokerAlreadyRegister() {
        assertNotNull(exception);
    }

    @But("^with error message: \"([^\"]*)\"")
    public void withErrorMessage(String errorMessage) {
        assertEquals(errorMessage, exception.getMessage());
    }

    public Broker getBroker() {
        return broker;
    }
}