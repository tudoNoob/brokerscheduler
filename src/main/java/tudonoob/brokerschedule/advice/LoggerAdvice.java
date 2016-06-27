package tudonoob.brokerschedule.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.jboss.logging.Logger;
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

    public Logger createLog(JoinPoint joinPoint) {
        return Logger.getLogger(joinPoint.getSignature().getName());
    }

    @Before("execution(* tudonoob.brokerschedule.*.*..*(..))")
    public void processAnnotaionLoggger(JoinPoint joinPoint) {
        LogMethod annotation = getLogMethodAnnotation(joinPoint);

        if (annotation != null) {
            executeLogForAppropriateLevel(joinPoint, annotation);
        }
    }

    private void executeLogForAppropriateLevel(JoinPoint joinPoint, LogMethod annotation) {
        Logger logger = createLog(joinPoint);

        String level = annotation.level().toLowerCase();
        String message = createMessage(annotation, joinPoint);

        Map<String, BiConsumer<String, Logger>> map = buildMap();
        map.get(level).accept(message, logger);
    }

    private String createMessage(LogMethod annotation, JoinPoint joinPoint) {
        String message = annotation.message();

        if (message.contains("%s")) {
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

    private Map<String, BiConsumer<String, Logger>> buildMap() {
        Map<String, BiConsumer<String, Logger>> map = new HashMap<>();
        map.put("warn", (message, logger) -> logger.warn(message));
        map.put("debug", (message, logger) -> logger.debug(message));
        map.put("info", (message, logger) -> logger.info(message));
        map.put("error", (message, logger) -> logger.error(message));
        return map;
    }

}
