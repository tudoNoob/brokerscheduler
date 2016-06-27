package tudonoob.brokerschedule.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is to indicate that such method must be logged.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogMethod {

    String DEFAULT_MESSAGE = "Executing %s.";

    /**
     * This attribute is to set the log level.
     */
    String level() default "debug";

    /**
     * This attribute is the message to be logged.
     * The default message is: Executing {method name}.
     */
    String message() default DEFAULT_MESSAGE;

}
