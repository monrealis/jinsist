package jinsist.expectations;

import jinsist.matchers.Arguments;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class OrderedExpectations implements Expectations {
    private List<Expectation<?, ?>> expectations = new ArrayList<>();
    private boolean wasUnexpectedInvocation = false;

    @Override
    public <ReturnType, MockType> void recordStub(
            MockInstance<MockType> mockInstance,
            Method method,
            Arguments arguments,
            ReturnType result
    ) {
        ExpectedInvocation<MockType> invocation = new ExpectedInvocation<>(mockInstance.getMockClass(), mockInstance.getInstance(), method, arguments);
        expectations.add(new Expectation<>(invocation, result));
    }

    @Override
    public <MockType> Object execute(
            MockInstance<MockType> mockInstance,
            MockType instance,
            Method method,
            Object[] arguments
    ) {
        Invocation<MockType> invocation = new Invocation<>(mockInstance.getMockClass(), instance, method, arguments);
        if (expectations.isEmpty()) {
            throw new UnexpectedInvocation(invocation);
        }
        Expectation<?, ?> expectation = expectations.remove(0);

        verifyExpectationMatchesInvocation(expectation, invocation);

        return expectation.getResult();
    }

    private <MockType> void verifyExpectationMatchesInvocation(
            Expectation<?, ?> expectation,
            Invocation<MockType> invocation
    ) {
        if (!expectation.isFor(invocation)) {
            wasUnexpectedInvocation = true;
            throw new UnexpectedInvocation(expectation, invocation);
        }
    }

    @Override
    public void verify() {
        if (!expectations.isEmpty() || wasUnexpectedInvocation) {
            throw new UnmetExpectations();
        }
    }
}
