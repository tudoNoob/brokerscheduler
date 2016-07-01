package tudonoob.brokerschedule;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import tudonoob.brokerschedule.controller.BrokerController;
import tudonoob.brokerschedule.model.BrokerModel;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BrokerBulkStep {

    @Autowired
    private BrokerController controller;

    @Autowired
    private BrokerRegistrationStep registrationStep;

    private List<BrokerModel> bulkBrokerModels;

    private List<BrokerModel> responseBrokerModels;

    public BrokerBulkStep() {
        bulkBrokerModels = new ArrayList<>();
    }

    @And("add broker to bulk")
    public void add_broker_to_bulk() {
        bulkBrokerModels.add(registrationStep.getBrokerModel());
    }

    @When("I bulk this list of brokers")
    public void i_bulk_this_list_of_brokers() {
        responseBrokerModels = controller.addBulkOfBrokers(bulkBrokerModels);
    }

    @Then("return the same list that I bulk")
    public void return_the_same_list_that_i_bulk() {
        assertEquals(responseBrokerModels, bulkBrokerModels);
    }

}
