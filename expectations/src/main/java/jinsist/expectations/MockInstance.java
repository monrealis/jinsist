package jinsist.expectations;

import static java.util.Arrays.asList;
import static jinsist.expectations.ValueObjects.equal;

import java.util.List;

public class MockInstance<T> {
    private final Class<T> mockClass;
    private final T instance;

    public MockInstance(Class<T> mockClass, T instance) {
        this.mockClass = mockClass;
        this.instance = instance;
    }

    public Class<T> getMockClass() {
        return mockClass;
    }

    public T getInstance() {
        return instance;
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
        return asList(mockClass, instance);
    }
}
