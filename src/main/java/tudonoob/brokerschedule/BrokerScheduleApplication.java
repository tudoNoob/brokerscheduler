package tudonoob.brokerschedule;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
@EnableAspectJAutoProxy
public class BrokerScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(BrokerScheduleApplication.class, args);
    }

    @Bean
    @Qualifier("brokerCache")
    public Map<String, Object> buildCache() {
        return new ConcurrentHashMap<>();
    }


}
