package tudonoob.brokerschedule.advice;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.Arrays;

@ToString
@AllArgsConstructor
public class ClassAttributes {

    private String methodName;

    private String argument;

    private String returnType;

    public static ClassAttributes build(JoinPoint joinPoint) {

        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        String arguments = Arrays.toString(joinPoint.getArgs());

        final MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        String returnType = method.getReturnType().getName();

        return new ClassAttributes(methodName, arguments, returnType);

    }
}
