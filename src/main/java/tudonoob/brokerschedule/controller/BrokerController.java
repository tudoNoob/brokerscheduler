package tudonoob.brokerschedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tudonoob.brokerschedule.cache.BrokerCache;
import tudonoob.brokerschedule.domain.Broker;
import tudonoob.brokerschedule.model.ErrorMessage;

import javax.validation.Valid;

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

    @RequestMapping(value = "/update/{id}")
    public
    @ResponseBody
    Broker updateBroker(@PathVariable("id") String id, @RequestBody Broker broker) {
        return cache.updateBroker(id, broker);
    }

    @ExceptionHandler(RuntimeException.class)
    public ErrorMessage handleError(Exception exception) {
        return new ErrorMessage(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
