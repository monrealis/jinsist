package jinsist;

import jinsist.expectations.Expectations;
import jinsist.expectations.OrderedExpectations;
import jinsist.expectations.ReportExpectations;
import jinsist.mock.Mock;

import java.util.IdentityHashMap;
import java.util.Map;

public class Mockery {
    private Map<Object, Mock<?>> mocks = new IdentityHashMap<>();
    private Expectations expectations = new ReportExpectations(new OrderedExpectations());

    public void verify() {
        expectations.verify();
    }

    public <MockType> MockType mock(Class<MockType> classToMock) {
        Mock<MockType> mock = new Mock<>(classToMock, expectations);
        MockType instance = mock.getInstance();
        mocks.put(instance, mock);
        return instance;
    }

    public <M> Mock<M> expect(M mockInstance) {
        ensureMockExists(mockInstance);
        Mock<M> m = getMock(mockInstance);
        return m;
    }

    private <M> void ensureMockExists(M mockInstance) {
        if (mocks.containsKey(mockInstance))
            return;
        String error = String.format("Given object is not a mock known to this mockery", mockInstance);
        throw new IllegalArgumentException(error);
    }

    @SuppressWarnings("unchecked")
    private <M> Mock<M> getMock(M mockInstance) {
        return (Mock<M>) mocks.get(mockInstance);
    }
}
