package tudonoob.brokerschedule.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import tudonoob.brokerschedule.cache.BrokerCache;
import tudonoob.brokerschedule.domain.Broker;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class BrokerServiceTest {

    @Mock
    private BrokerCache cache;

    @InjectMocks
    private BrokerService service;

    private ConcurrentMap<String, Object> brokersMocked;

    private Broker[] brokers;


    @Before
    public void setUp() throws Exception {
        initMocks(this);
        service = new BrokerService(cache);
        brokersMocked = new BrokersBuilder().buildBrokersMockFromFile();
    }


    @Test
    public void shouldReturnEmptyListGivenNoValidName() throws Exception {
        when(cache.getAllBrokers()).thenReturn(brokersMocked);
        List<Object> responseList = service.filterBrokersByName("inValidName");
        assertEquals(0, responseList.size());
    }

    @Test
    public void shouldReturnAListWithSizeZeroGivenAValidName() throws Exception {
        when(cache.getAllBrokers()).thenReturn(brokersMocked);
        List<Object> responseList = service.filterBrokersByName("William");
        assertEquals(1, responseList.size());
    }


    @Test
    public void shouldReturnAListWithSizeOneGivenANoValidConstrain() {
        when(cache.getAllBrokers()).thenReturn(brokersMocked);

        List<Object> resultBrokers = service.filterBrokersByConstraint("friday");

        boolean allMatch = resultBrokers.stream().allMatch(broker ->
                ((Broker) broker).getConstrains()
                        .stream()
                        .anyMatch(constraint -> constraint.getDayName().equals("friday"))
        );

        assertTrue(allMatch);
    }
}