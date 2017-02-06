package jinsist.mock;

class MockInstance<T> {
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
}
