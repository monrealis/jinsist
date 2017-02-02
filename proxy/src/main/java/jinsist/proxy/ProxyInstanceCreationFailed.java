package jinsist.proxy;

class ProxyInstanceCreationFailed extends RuntimeException {
    private static final long serialVersionUID = 1L;

    <T> ProxyInstanceCreationFailed(Class<T> type, Throwable e) {
        super("Cannot create proxy for " + type + ". Make sure your type is public.", e);
    }
}
