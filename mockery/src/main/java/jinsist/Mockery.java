package jinsist;

import static java.util.Objects.requireNonNull;

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
        Mock<M> m = findMock(mockInstance);
        requireNonNull(m);
        return m;
    }

    @SuppressWarnings("unchecked")
    private <M> Mock<M> findMock(M mockInstance) {
        return (Mock<M>) mocks.get(mockInstance);
    }
}
