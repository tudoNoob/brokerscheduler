package tudonoob.brokerschedule.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import tudonoob.brokerschedule.cache.BrokerCache;
import tudonoob.brokerschedule.model.BrokerModel;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class BrokerModelServiceTest {

    @Mock
    private BrokerCache cache;

    @InjectMocks
    private BrokerService service;

    private ConcurrentMap<String, Object> brokersMocked;


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
        assertEquals(2, responseList.size());
    }


    @Test
    public void shouldReturnAListWithSizeOneGivenANoValidConstrain() {
        when(cache.getAllBrokers()).thenReturn(brokersMocked);

        List<Object> resultBrokers = service.filterBrokersByConstraint("friday");

        assertEquals(1, resultBrokers.size());

        boolean allMatch = resultBrokers.stream().allMatch(broker ->
                ((BrokerModel) broker).getConstrains()
                        .stream()
                        .anyMatch(constraint -> constraint.getDayName().equals("friday"))
        );

        assertTrue(allMatch);
    }

    @Test
    public void shouldReturnEmptyListWhenITryToFindTheBrokersWithOnlyTheConstraintMonday() throws Exception {
        when(cache.getAllBrokers()).thenReturn(brokersMocked);

        List<Object> brokers = service.filterBrokersByOnlyOneConstraint("monday");

        assertEquals(0, brokers.size());
    }

    @Test
    public void shouldReturnListWithSizeOneWhenITryToFindTheBrokerWithOnlyTheConstraintThursday() throws Exception {
        when(cache.getAllBrokers()).thenReturn(brokersMocked);

        List<Object> brokers = service.filterBrokersByOnlyOneConstraint("thursday");

        assertEquals(1, brokers.size());
    }

    @Test
    public void shouldReturnListWithSizeOneWhenITryToFindTheBrokerWithOnlyTheConstraintWednesday() throws Exception {
        when(cache.getAllBrokers()).thenReturn(brokersMocked);

        List<Object> brokers = service.filterBrokersByOnlyOneConstraint("wednesday");

        assertEquals(1, brokers.size());
    }
}