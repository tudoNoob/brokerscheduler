package tudonoob.brokerschedule;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BrokerScheduleApplication.class,loader = SpringApplicationContextLoader.class)
@WebAppConfiguration
@IntegrationTest
public class BrokerScheduleApplicationTests {

	@Test
	public void contextLoads() {
	}

}
