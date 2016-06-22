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

    /**
     * This attribute is to set the log level.
     */
    String level() default "debug";

    /**
     * This attribute is the message to be logged.
     */
    String message() default "";

}
