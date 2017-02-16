package jinsist.mock;

import jinsist.expectations.Expectations;
import jinsist.expectations.MockInstance;
import jinsist.mock.delegators.MockExecutor;
import jinsist.mock.delegators.SetupRecorder;
import jinsist.proxy.Delegator;
import jinsist.proxy.Proxy;

public class Mock<MockType> {
    private MockInstance<MockType> mockInstance;
    private Expectations expectations;

    public Mock(Class<MockType> mockClass, Expectations expectations) {
        MockType instance = new Proxy<>(mockClass).instance(new MockExecutor<>(expectations, mockClass));
        this.mockInstance = new MockInstance<MockType>(mockClass, instance);
        this.expectations = expectations;
    }

    public <ReturnType> Returns<ReturnType, MockType> query(StubCall<ReturnType, MockType> call) {
        return new Returns<>(call, this);
    }

    Voids<MockType> command(ExpectationCall<MockType> call) {
        return new Voids<>(call, this);
    }

    <ReturnType> MockType setupInstanceWithResult(ReturnType result, SetupResult setupResult) {
        Delegator<MockType> recorder = new SetupRecorder<>(expectations, mockInstance, result, setupResult);

        return new Proxy<>(getMockClass()).instance(recorder);
    }

    private Class<MockType> getMockClass() {
        return mockInstance.getMockClass();
    }

    public MockType getInstance() {
        return mockInstance.getInstance();
    }

    MockType setupInstance(SetupResult setupResult) {
        return setupInstanceWithResult(null, setupResult);
    }
}
