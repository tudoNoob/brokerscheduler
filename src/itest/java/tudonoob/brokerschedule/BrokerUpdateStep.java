package tudonoob.brokerschedule;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import tudonoob.brokerschedule.controller.BrokerController;
import tudonoob.brokerschedule.model.BrokerModel;

import java.util.concurrent.ConcurrentMap;

import static org.junit.Assert.assertEquals;

public class BrokerUpdateStep {

    @Autowired
    private BrokerRegistrationStep registrationStep;

    @Autowired
    private BrokerController controller;

    private BrokerModel responseBrokerModel;

    private String id;

    @When("I update the broker with the id \"([^\"]*)\"")
    public void i_update_the_broker_with_the_id(String id) {
        this.id = id;
        BrokerModel brokerModel = registrationStep.getBrokerModel();
        responseBrokerModel = controller.updateBroker(id, brokerModel);
    }

    @Then("I will have a broker with the name \"([^\"]*)\"")
    public void i_will_receive_a_broker_with_the_name(String brokerName) {
        assertEquals(brokerName, responseBrokerModel.getName());
        ConcurrentMap<String, Object> allBrokers = controller.getAllBrokers();
        BrokerModel expectedBrokerModel = (BrokerModel) allBrokers.get(this.id);
        assertEquals(brokerName, expectedBrokerModel.getName());
    }


}
