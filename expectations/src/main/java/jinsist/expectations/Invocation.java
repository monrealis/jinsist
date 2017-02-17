package jinsist.expectations;

import static java.util.Arrays.asList;
import static jinsist.expectations.ValueObjects.equal;

import jinsist.report.FormattedMethod;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class Invocation<MockType> {
    private final Class<MockType> mockClass;
    private final Class<?> instance;
    private final Method method;
    private final Object[] arguments;

    Invocation(MockInstance<MockType> mockInstance, Method method, Object[] arguments) {
        this.mockClass = mockInstance.getMockClass();
        this.instance = mockInstance.getInstanceClass();
        this.method = method;
        this.arguments = arguments;
    }

    @Override
    public String toString() {
        return formatInvocation();
    }

    private String formatInvocation() {
        return new FormattedMethod(mockClass, method, Arrays.asList(arguments)).toString();
    }

    Class<?> getInstance() {
        return instance;
    }

    Method getMethod() {
        return method;
    }

    Object[] getArguments() {
        return arguments;
    }

    @Override
    public boolean equals(Object obj) {
        return equal(this, obj, o -> toList().equals(o.toList()));
    }

    @Override
    public int hashCode() {
        return toList().hashCode();
    }

    private List<?> toList() {
        // TODO monrealis 2017-02-09 why not all?
        return asList(instance, method, asList(arguments));
    }
}
