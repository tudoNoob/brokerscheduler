package tudonoob.brokerschedule.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import tudonoob.brokerschedule.cache.BrokerCache;
import tudonoob.brokerschedule.domain.Broker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class BrokerServiceTest {

    @Mock
    private BrokerCache cache;

    @InjectMocks
    private BrokerService service;

    private ConcurrentMap<String, Object> brokersMocked;


    @Before
    public void setUp() throws Exception {
        initMocks(this);
        service = new BrokerService(cache);
        brokersMocked = new ConcurrentHashMap<>();
        brokersMocked.put("1", new Broker("Maria"));
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
        List<Object> responseList = service.filterBrokersByName("Maria");
        assertEquals(1, responseList.size());
    }
}