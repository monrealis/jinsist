package jinsist.mock.testtypes;

import jinsist.expectations.Expectations;
import jinsist.expectations.MockInstance;
import jinsist.matchers.Arguments;
import jinsist.matchers.EqualsMatcher;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.stream;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

public class TestFactories {

    protected List<Stub<?, ?>> registry = new ArrayList<>();
    protected Method firstMethod = Collaborator.class.getMethod("firstMethod", String.class);
    protected Method voidMethod = Collaborator.class.getMethod("voidMethod");
    protected String input = "value";

    protected TestFactories() throws NoSuchMethodException {
    }

    protected Arguments argumentsOf(Object... values) {
        return new Arguments(stream(values).map(EqualsMatcher::new).collect(toList()));
    }

    protected <MockType, ReturnType> Stub<MockType, ReturnType> stub(
            Class<MockType> type, Method method, Arguments arguments, ReturnType result
    ) {
        return new Stub<>(type, method, arguments, result);
    }

    protected <MockType, ReturnType> Stub<MockType, ReturnType> stub(
            Class<MockType> type, Method method, Arguments arguments
    ) {
        return new Stub<>(type, method, arguments, null);
    }

    protected <MockType, ReturnType> Stub<MockType, ReturnType> stub(Class<MockType> type, Method method) {
        return new Stub<>(type, method, new Arguments(emptyList()), null);
    }

    protected Expectations expectations = new Expectations() {

        @Override
        public <ReturnType, MockType> void recordStub(
                MockInstance<MockType> mockInstance, Method method, Arguments arguments, ReturnType result
        ) {
            registry.add(new Stub<>(mockInstance.getMockClass(), method, arguments, result));
        }

        @Override
        public <MockType> Object execute(
                MockInstance<MockType> mockInstance, Method method, Object[] arguments
        ) {
            return null;
        }

        @Override
        public void verify() {

        }
    };
}
