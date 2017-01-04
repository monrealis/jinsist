package com.github.liucijus.jinsist.expectations;

public class UnexpectedInvocation extends RuntimeException {
    UnexpectedInvocation(Expectation expectation, Invocation invocation) {
        super("Expected " + expectation.getExpectedInvocation() + ", but was " + invocation);
    }

    UnexpectedInvocation(String message, UnexpectedInvocation e) {
        super(message, e);
    }
}