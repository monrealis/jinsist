package jinsist.mock;

class MethodToStubNotFound extends RuntimeException {
    private static final long serialVersionUID = 1L;

    MethodToStubNotFound() {
        super("Method not found while stubbing. Make sure public method is invoked under stubbing.");
    }
}
