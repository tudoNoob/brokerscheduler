package tudonoob.brokerschedule.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import tudonoob.brokerschedule.annotation.LogMethod;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

@Component
@Aspect
public class LoggerAdvice {


    @Before("execution(* tudonoob.brokerschedule.*.*Controller..*(..))")
    public void logControllerLayer(JoinPoint joinPoint) {
        Logger LOGGER = createLog(joinPoint);

        ClassAttributes classAttributes = ClassAttributes.build(joinPoint);

        LOGGER.infof("Executing the following class/method: %s", classAttributes.toString());
    }

    @Before("execution(* tudonoob.brokerschedule.*.*..*(..))")
    public void processAnnotaionLoggger(JoinPoint joinPoint) {
        LogMethod annotation = getLogMethodAnnotation(joinPoint);

        if (annotation != null) {
            executeLogForAppropriateLevel(joinPoint, annotation);
        }
    }

    private Logger createLog(JoinPoint joinPoint) {
        return Logger.getLogger(joinPoint.getSignature().getName());
    }

    private void executeLogForAppropriateLevel(JoinPoint joinPoint, LogMethod annotation) {
        Logger logger = createLog(joinPoint);

        Level level = getLoggerLevel(annotation);
        String message = createMessage(annotation, joinPoint);

        Map<Level, BiConsumer<String, Logger>> map = buildMap();
        map.get(level).accept(message, logger);
    }

    private Level getLoggerLevel(LogMethod annotation) {

        String level = annotation.level();
        return Level.valueOf(level);
    }

    private String createMessage(LogMethod annotation, JoinPoint joinPoint) {
        String message = annotation.message();

        if (message.equals(annotation.DEFAULT_MESSAGE)) {
            ClassAttributes build = ClassAttributes.build(joinPoint);
            message = String.format(message, build.getMethodName());
        }

        return message;
    }

    private LogMethod getLogMethodAnnotation(JoinPoint joinPoint) {
        Method method = getMethod(joinPoint);

        LogMethod annotation = method.getAnnotation(LogMethod.class);

        return annotation;
    }

    private Method getMethod(JoinPoint joinPoint) {
        final MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        return methodSignature.getMethod();
    }

    private Map<Level, BiConsumer<String, Logger>> buildMap() {
        Map<Level, BiConsumer<String, Logger>> map = new HashMap<>();
        map.put(Level.WARN, (message, logger) -> logger.warn(message));
        map.put(Level.DEBUG, (message, logger) -> logger.debug(message));
        map.put(Level.INFO, (message, logger) -> logger.info(message));
        map.put(Level.ERROR, (message, logger) -> logger.error(message));
        return map;
    }

}
