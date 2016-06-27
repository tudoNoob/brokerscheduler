package tudonoob.brokerschedule;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BrokerScheduleApplication.class, loader = SpringApplicationContextLoader.class)
@WebIntegrationTest(randomPort = true)
public class BrokerScheduleApplicationTests {


    @Test
    public void contextLoads() {
    }


}
