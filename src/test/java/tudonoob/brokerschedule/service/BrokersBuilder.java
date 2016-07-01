package tudonoob.brokerschedule.service;

import com.google.gson.Gson;
import tudonoob.brokerschedule.model.BrokerModel;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class BrokersBuilder {


    public ConcurrentHashMap<String, Object> buildBrokersMockFromFile() {
        ConcurrentHashMap<String, Object> brokersMocked = new ConcurrentHashMap<>();

        List<BrokerModel> brokersList = getBrokersFromFile();

        buildMapOfBrokers(brokersMocked, brokersList);

        return brokersMocked;
    }

    private ConcurrentHashMap<String, Object> buildMapOfBrokers(ConcurrentHashMap<String, Object> brokersMocked, List<BrokerModel> brokersList) {
        final int[] accumulator = {1};
        brokersList.forEach(broker -> {

            brokersMocked.put(new StringBuilder().append(accumulator[0]).toString(), broker);
            accumulator[0] += 1;
        });

        return brokersMocked;
    }

    private List<BrokerModel> getBrokersFromFile() {
        Gson gson = new Gson();
        BrokerModel[] brokerModels = gson.fromJson(new InputStreamReader(
                getClass().getResourceAsStream("/brokers.json")), BrokerModel[].class);
        return Arrays.asList(brokerModels);
    }


}
