package tudonoob.brokerschedule.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.Arrays;

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

    public ClassAttributes(String methodName, String argument, String returnType) {
        this.methodName = methodName;
        this.argument = argument;
        this.returnType = returnType;
    }


    @Override
    public String toString() {
        return "ClassAttributes [methodName=" + methodName + ", argument=" + argument + ", returnType=" + returnType
                + "]";
    }
}
