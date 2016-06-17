package tudonoob.brokerschedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tudonoob.brokerschedule.cache.BrokerCache;
import tudonoob.brokerschedule.cache.BrokerScheduleException;
import tudonoob.brokerschedule.domain.Broker;
import tudonoob.brokerschedule.model.ErrorMessage;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
public class BrokerController {

    @Autowired
    private BrokerCache cache;

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


    @ExceptionHandler(BrokerScheduleException.class)
    public ErrorMessage handleError(BrokerScheduleException exception) {
        return new ErrorMessage(exception.getMessage(), exception.getExceptionName(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/bulkBrokers", method = RequestMethod.POST)
    public
    @ResponseBody
    List<Broker> addBulkOfBrokers(@RequestBody List<Broker> brokers) {
        brokers.forEach((broker) -> cache.addToCache(broker));
        return brokers;
    }

    @RequestMapping(value = "/filterByName/{name}", method = RequestMethod.POST)
    public List<Object> filterByNameMatcher(@PathVariable("name") String name) {

        Predicate<Object> matchBrokerByName = (broker) -> ((Broker) broker).getName().contains(name);

        List<Object> collect = cache.getAllBrokers()
                .values().stream()
                .filter(matchBrokerByName)
                .collect(Collectors.toList());

        return collect;
    }

    @RequestMapping(value = "/filterByConstrain/{constrain}")
    public List<Object> filterByConstrain(@PathVariable("constrain") String constrain) {

        Predicate<Object> matchBrokerByDayName = (broker) -> ((Broker) broker).getConstrains()
                .stream().anyMatch(day -> day.getDayName().equals(constrain));

        List<Object> brokersWhichHasTheSameConstrain = cache.getAllBrokers().values()
                .stream().filter(matchBrokerByDayName).collect(Collectors.toList());

        return brokersWhichHasTheSameConstrain;
    }

}
