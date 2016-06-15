package tudonoob.brokerschedule;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import tudonoob.brokerschedule.controller.BrokerController;
import tudonoob.brokerschedule.domain.Broker;

import static org.junit.Assert.assertEquals;

public class BrokerUpdateStep {

    @Autowired
    private BrokerRegistrationStep registrationstep;

    @Autowired
    private BrokerController controller;

    private Broker responseBroker;

    @When("I update the broker with the id \"([^\"]*)\"")
    public void i_update_the_broker_with_the_id(String id) {
        Broker broker = registrationstep.getBroker();
        responseBroker = controller.updateBroker(id, broker);
    }

    @Then("I will receive a broker with the name \"([^\"]*)\"")
    public void i_will_receive_a_broker_with_the_name(String brokerName) {
        assertEquals(brokerName, responseBroker.getName());
    }


}
