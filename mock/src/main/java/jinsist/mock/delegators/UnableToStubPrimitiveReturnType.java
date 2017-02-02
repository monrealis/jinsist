package jinsist.mock.delegators;

public class UnableToStubPrimitiveReturnType extends RuntimeException {
    private static final long serialVersionUID = 1L;

    UnableToStubPrimitiveReturnType() {
        super("Methods with primitive return types must be stubbed to return value.");
    }
}
