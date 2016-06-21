package tudonoob.brokerschedule.service;

import com.google.gson.Gson;
import tudonoob.brokerschedule.domain.Broker;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class BrokersBuilder {


    public ConcurrentHashMap<String, Object> buildBrokersMockFromFile() {
        ConcurrentHashMap<String, Object> brokersMocked = new ConcurrentHashMap<>();

        List<Broker> brokersList = getBrokersFromFile();

        buildMapOfBrokers(brokersMocked, brokersList);

        return brokersMocked;
    }

    private void buildMapOfBrokers(ConcurrentHashMap<String, Object> brokersMocked, List<Broker> brokersList) {

        brokersList.forEach(broker -> {
            int accumulator = 1;
            brokersMocked.put(new StringBuilder().append(accumulator).toString(), broker);
            accumulator += 1;
        });

    }

    private List<Broker> getBrokersFromFile() {
        Gson gson = new Gson();
        Broker[] brokers = gson.fromJson(new InputStreamReader(
                getClass().getResourceAsStream("/brokers.json")), Broker[].class);
        return Arrays.asList(brokers);
    }


}
