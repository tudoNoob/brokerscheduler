package tudonoob.brokerschedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tudonoob.brokerschedule.cache.BrokerCache;
import tudonoob.brokerschedule.model.BrokerModel;
import tudonoob.brokerschedule.service.BrokerService;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

@RestController
public class BrokerController {

    @Autowired
    private BrokerCache cache;

    @Autowired
    private BrokerService service;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public
    @ResponseBody
    BrokerModel registerBroker(@RequestBody @Valid BrokerModel brokerModel) {
        return cache.addToCache(brokerModel);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    BrokerModel updateBroker(@PathVariable("id") String id, @RequestBody BrokerModel brokerModel) {
        return cache.updateBroker(id, brokerModel);
    }

    @RequestMapping(value = "/getAllBrokers", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    ConcurrentMap<String, Object> getAllBrokers() {
        return cache.getAllBrokers();
    }

    @RequestMapping(value = "bulkBrokers", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public
    @ResponseBody
    List<BrokerModel> addBulkOfBrokers(@RequestBody List<BrokerModel> brokerModels) {
        brokerModels.forEach((broker) -> cache.addToCache(broker));
        return brokerModels;
    }

    @RequestMapping(value = "/filterByName/{name}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public List<Object> filterByNameMatcher(@PathVariable("name") String name) {
        return service.filterBrokersByName(name);
    }

    @RequestMapping(value = "/filterByConstrain/{constrain}")
    @ResponseStatus(HttpStatus.OK)
    public List<Object> filterByConstrain(@PathVariable("constrain") String constrain) {
        return service.filterBrokersByConstraint(constrain);
    }

    @RequestMapping(value = "/clearCache", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clearCache() {
        cache.clear();
    }

}
