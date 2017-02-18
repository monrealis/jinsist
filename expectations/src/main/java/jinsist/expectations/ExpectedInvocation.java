package jinsist.expectations;

import static java.util.Arrays.asList;
import static jinsist.matchers.ValueObjects.equal;

import jinsist.matchers.Arguments;
import jinsist.report.FormattedMethod;

import java.lang.reflect.Method;
import java.util.List;

public class ExpectedInvocation<MockType> {
    private final Class<MockType> mockClass;
    private final Class<?> instanceClass;
    private final Method method;
    private final Arguments arguments;

    ExpectedInvocation(MockInstance<MockType> mockInstance, Method method, Arguments arguments) {
        this.mockClass = mockInstance.getMockClass();
        this.instanceClass = mockInstance.getInstanceClass();
        this.method = method;
        this.arguments = arguments;
    }

    @Override
    public String toString() {
        return formatInvocation();
    }

    private String formatInvocation() {
        return new FormattedMethod(mockClass, method, arguments.getArgumentMatchers()).toString();
    }

    Class<?> getInstance() {
        return instanceClass;
    }

    Method getMethod() {
        return method;
    }

    Arguments getArguments() {
        return arguments;
    }

    @Override
    public boolean equals(Object other) {
        return equal(this, other, o -> toList().equals(o.toList()));
    }

    @Override
    public int hashCode() {
        return toList().hashCode();
    }

    private List<?> toList() {
        return asList(mockClass, instanceClass, method, arguments);
    }
}
