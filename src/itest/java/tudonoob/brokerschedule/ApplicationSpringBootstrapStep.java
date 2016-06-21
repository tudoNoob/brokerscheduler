package tudonoob.brokerschedule;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;
import tudonoob.brokerschedule.controller.BrokerController;


public class ApplicationSpringBootstrapStep extends BrokerScheduleApplicationTests {

    @Autowired
    private BrokerController controller;

    @After
    public void clearCache() {
        controller.clearCache();
    }

    @Given("I bootstrap the spring application")
    public void bootstrapApplication() {

    }

}
