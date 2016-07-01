package tudonoob.brokerschedule.adapter;

import org.junit.Before;
import org.junit.Test;
import tudonoob.brokerschedule.domainmodel.Broker;
import tudonoob.brokerschedule.model.BrokerModel;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;

public class BrokerAdapterTest {

    private BrokerAdapter adapter;

    @Before
    public void setUp() throws Exception {
        adapter = new BrokerAdapter();
    }

    @Test(expected = RuntimeException.class)
    public void shouldBeAbleToThrowExceptionForNotHavingTheNameRight() throws Exception {
        adapter.adaptBroker(new BrokerModel());
    }

    @Test
    public void shouldBeAbeToCreateABroker() throws Exception {
        BrokerModel model = new BrokerModel().builder().name("fernanda").constrains(new ArrayList<>()).build();
        Broker broker = adapter.adaptBroker(model);
        assertNotNull(broker);
    }
}
