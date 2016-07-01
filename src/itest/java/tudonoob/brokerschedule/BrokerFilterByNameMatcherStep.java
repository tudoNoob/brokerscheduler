package tudonoob.brokerschedule;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import tudonoob.brokerschedule.controller.BrokerController;
import tudonoob.brokerschedule.model.Broker;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class BrokerFilterByNameMatcherStep {

    @Autowired
    private BrokerController controller;
    private boolean onlyHasInTheArrayMatcherForTheName;

    @When("I search brokers for the name \"([^\"]*)\"")
    public void i_search_brokers_for_the_name(String name) {
        List<Object> objects = controller.filterByNameMatcher(name);
        onlyHasInTheArrayMatcherForTheName = objects
                .stream()
                .allMatch((broker) -> ((Broker) broker).getName().contains(name));
    }

    @Then("should bring the list with only the brokers that match the name \"([^\"]*)\"")
    public void should_bring_the_list_with_only_the_brokers_that_match_the_name(String name) {
        assertTrue(onlyHasInTheArrayMatcherForTheName);
    }


}
