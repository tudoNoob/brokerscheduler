package tudonoob.brokerschedule;

import cucumber.api.java.en.*;
import org.springframework.beans.factory.annotation.Autowired;
import tudonoob.brokerschedule.controller.BrokerController;
import tudonoob.brokerschedule.model.BrokerModel;
import tudonoob.brokerschedule.model.DayModel;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class BrokerRegistrationStep {

    @Autowired
    private BrokerController controller;


    private BrokerModel brokerModel;

    private List<DayModel> constrains;

    private RuntimeException exception;

    @Given("^a broker with this name \"([^\"]*)\"$")
    public void i_have_broker_with_name(String brokerName) throws Throwable {
        this.brokerModel = new BrokerModel();
        constrains = new ArrayList<>();
        brokerModel.setConstrains(constrains);
        brokerModel.setName(brokerName);
    }

    @And("^this broker does not want to work on \"([^\"]*)\" in the shift \"(morning|afternoon|not\\savailable)\"$")
    public void _he_does_not_want_to_work_on(String dayName, String shift) {
        DayModel dayModel = new DayModel();
        dayModel.setDayName(dayName);

        if (shift.equals("afternoon")) {
            dayModel.setIsAvailableAfternoon(true);
        } else if (shift.equals("morning")) {
            dayModel.setIsAvailableMorning(true);
        }

        boolean isNotAvailableInTheWholeDay = (dayModel.getIsAvailableAfternoon() || dayModel.getIsAvailableMorning()) ?
                false : true;

        dayModel.setIsAvailableInTheWholeDay(isNotAvailableInTheWholeDay);
        constrains.add(dayModel);
    }

    @When("^register the broker")
    public void register() {
        try {
            controller.registerBroker(this.brokerModel);
        } catch (RuntimeException exception) {
            this.exception = exception;
        }
    }

    @Then("^the broker should be registered with this name (\\w+)$")
    public void my_belly_should_growl(String expectedBrokerName) throws Throwable {
        assertNull(exception);
        assertEquals(brokerModel.getName(), expectedBrokerName);
    }


    @Then("^as the broker is already register should throw runtimeException")
    public void brokerAlreadyRegister() {
        assertNotNull(exception);
    }

    @But("^with error message: \"([^\"]*)\"")
    public void withErrorMessage(String errorMessage) {
        assertEquals(errorMessage, exception.getMessage());
    }

    public BrokerModel getBrokerModel() {
        return brokerModel;
    }
}