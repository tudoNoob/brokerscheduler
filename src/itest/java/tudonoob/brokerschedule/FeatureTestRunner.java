package tudonoob.brokerschedule;

import cucumber.api.CucumberOptions;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.cs.A;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import tudonoob.brokerschedule.controller.BrokerController;
import tudonoob.brokerschedule.domain.Broker;

@RunWith(Cucumber.class)
@CucumberOptions(format = "pretty", features = "src/itest/resources")
public class FeatureTestRunner {



}
