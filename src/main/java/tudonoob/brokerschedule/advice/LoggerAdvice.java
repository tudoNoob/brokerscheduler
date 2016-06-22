package tudonoob.brokerschedule.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerAdvice {


    @Before("execution(* tudonoob.brokerschedule.*..*(..))")
    public void logServiceAccess(JoinPoint joinPoint) {
        Logger LOGGER = createLog(joinPoint);
        ClassAttributes classAttributes = ClassAttributes.buildClassAttributes(joinPoint);
        LOGGER.infof("Executing the following class/method: %s", classAttributes.toString());
    }

    public Logger createLog(JoinPoint joinPoint) {
        Logger LOGGER = Logger.getLogger(joinPoint.getSignature().getName());
        return LOGGER;
    }


}
