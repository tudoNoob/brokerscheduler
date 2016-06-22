package tudonoob.brokerschedule.controller;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tudonoob.brokerschedule.cache.BrokerCache;
import tudonoob.brokerschedule.cache.BrokerScheduleException;
import tudonoob.brokerschedule.domain.Broker;
import tudonoob.brokerschedule.model.ErrorMessage;
import tudonoob.brokerschedule.service.BrokerService;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

@RestController
public class BrokerController {

    private static final String CACHE_IS_CLEAR = "Cache IS CLEAR";

    @Autowired
    private BrokerCache cache;

    @Autowired
    private BrokerService service;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public
    @ResponseBody
    Broker registerBroker(@RequestBody @Valid Broker broker) {
        return cache.addToCache(broker);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public
    @ResponseBody
    Broker updateBroker(@PathVariable("id") String id, @RequestBody Broker broker) {
        return cache.updateBroker(id, broker);
    }

    @RequestMapping(value = "/getAllBrokers", method = RequestMethod.GET)
    public
    @ResponseBody
    ConcurrentMap<String, Object> getAllBrokers() {
        return cache.getAllBrokers();
    }

    @RequestMapping(value = "bulkBrokers", method = RequestMethod.POST)
    public
    @ResponseBody
    List<Broker> addBulkOfBrokers(@RequestBody List<Broker> brokers) {
        System.out.println(brokers);
        brokers.forEach((broker) -> cache.addToCache(broker));
        return brokers;
    }

    @RequestMapping(value = "/filterByName/{name}", method = RequestMethod.POST)
    public List<Object> filterByNameMatcher(@PathVariable("name") String name) {
        return service.filterBrokersByName(name);
    }

    @RequestMapping(value = "/filterByConstrain/{constrain}")
    public List<Object> filterByConstrain(@PathVariable("constrain") String constrain) {
        return service.filterBrokersByConstraint(constrain);
    }

    @RequestMapping(value = "/clearCache", method = RequestMethod.DELETE)
    public String clearCache() {
        cache.clear();
        return CACHE_IS_CLEAR;
    }

}
