package jinsist.expectations;

public class UnmetExpectations extends RuntimeException {
    private static final long serialVersionUID = 1L;

    UnmetExpectations() {
    }

    UnmetExpectations(String message, Throwable throwable) {
        super(message, throwable);
    }
}
