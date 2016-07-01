package tudonoob.brokerschedule;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import tudonoob.brokerschedule.controller.BrokerController;
import tudonoob.brokerschedule.model.BrokerModel;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class BrokerFilterByConstrainStep {

    @Autowired
    private BrokerController controller;

    private List<Object> responseBrokers;

    @When("I filter by constrain \"([^\"]*)\"")
    public void i_filter_by_constrain(String constrain) {
        responseBrokers = controller.filterByConstrain(constrain);
    }

    @Then("all the brokers in the list should have the constrian \"([^\"]*)\"")
    public void all_the_brokers_in_the_list_should_have_the_constrain(String constrain) {
        List<Object> result = responseBrokers.stream().filter((broker)
                -> ((BrokerModel) broker).getConstrains().stream().anyMatch((day)
                -> !day.getDayName().equals(constrain))).collect(Collectors.toList());

        assertEquals(0, result.size());
    }
}
