package jinsist.expectations;

import static java.util.Arrays.asList;

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
        if (obj == this)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        return toList().equals(((MockInstance<?>) obj).toList());
    }

    @Override
    public int hashCode() {
        return toList().hashCode();
    }

    private List<?> toList() {
        return asList(mockClass, instance);
    }
}
