package jinsist.expectations;

import static java.util.Arrays.asList;
import static jinsist.expectations.ValueObjects.equal;

import java.util.List;

public class Expectation<MockType, ReturnType> {
    private final ExpectedInvocation<MockType> expectedInvocation;
    private final ReturnType result;

    Expectation(ExpectedInvocation<MockType> expectedInvocation, ReturnType result) {
        this.expectedInvocation = expectedInvocation;
        this.result = result;
    }

    Object getResult() {
        return result;
    }

    boolean isFor(Invocation<?> invocation) {
        return this.expectedInvocation.getInstance().equals(invocation.getInstance())
                && this.expectedInvocation.getMethod().equals(invocation.getMethod())
                && this.expectedInvocation.getArguments().matches(invocation.getArguments());
    }

    ExpectedInvocation<MockType> getExpectedInvocation() {
        return expectedInvocation;
    }

    @Override
    public boolean equals(Object o) {
        return equal(this, o, () -> toList().equals(((Expectation<?, ?>) o).toList()));
    }

    @Override
    public int hashCode() {
        return toList().hashCode();
    }
    
    private List<?> toList() {
        return asList(expectedInvocation, result);
    }
}
