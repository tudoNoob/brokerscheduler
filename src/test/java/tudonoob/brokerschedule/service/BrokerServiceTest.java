package tudonoob.brokerschedule.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import tudonoob.brokerschedule.cache.BrokerCache;
import tudonoob.brokerschedule.domain.Broker;

import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
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
        buildingBrokersMock();
    }

    private void buildingBrokersMock() {
        brokersMocked = new ConcurrentHashMap<>();

        Gson gson = new Gson();
        brokers = gson.fromJson(new InputStreamReader(
                getClass().getResourceAsStream("/brokers.json")), Broker[].class);
        List<Broker> brokersList = Arrays.asList(this.brokers);

        brokersList.forEach(broker -> {
            int accumulator = 1;
            brokersMocked.put(new StringBuilder().append(accumulator).toString(), broker);
            accumulator += 1;
        });
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


    public void shoudlReturnAlistWithSizeZeroGivenANoValidConstrain() {
        when(cache.getAllBrokers()).thenReturn(brokersMocked);
        service.filterBrokersByConstraint("friday");
    }
}