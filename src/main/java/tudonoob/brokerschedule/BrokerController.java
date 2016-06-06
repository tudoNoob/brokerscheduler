package tudonoob.brokerschedule;

import org.springframework.web.bind.annotation.*;

@RestController
public class BrokerController {


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public
    @ResponseBody
    Broker registerBroker(@RequestBody Broker broker) {
        return broker;
    }
}
