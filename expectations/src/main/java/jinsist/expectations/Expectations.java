package jinsist.expectations;

import jinsist.matchers.Arguments;

import java.lang.reflect.Method;

public interface Expectations {
    <ReturnType, MockType> void recordStub(
            MockInstance<MockType> mockInstance,
            Method method,
            Arguments arguments,
            ReturnType result
    );

    <MockType> Object execute(
            MockInstance<MockType> mockInstance,
            MockType instance,
            Method method,
            Object[] arguments
    );

    void verify();
}
