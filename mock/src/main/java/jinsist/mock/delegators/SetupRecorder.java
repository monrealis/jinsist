package jinsist.mock.delegators;

import jinsist.expectations.Expectations;
import jinsist.expectations.MockInstance;
import jinsist.matchers.Arguments;
import jinsist.matchers.EqualsMatcher;
import jinsist.mock.SetupResult;
import jinsist.proxy.Delegator;

import java.lang.reflect.Method;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class SetupRecorder<ReturnType, MockType> implements Delegator<MockType> {
    private final MockInstance<MockType> mockInstance;
    private final ReturnType result;
    private final SetupResult setupResult;
    private final Expectations expectations;

    public SetupRecorder(
            Expectations expectations,
            MockInstance<MockType> mockInstance,
            ReturnType result,
            SetupResult setupResult
    ) {
        this.expectations = expectations;
        this.mockInstance = mockInstance;
        this.result = result;
        this.setupResult = setupResult;
    }

    @Override
    public ReturnType handle(MockType setupInstance, Method method, Object[] args) {
        verifyReturnTypeNeedsToBeStubbed(result, method);

        Arguments arguments = makeArguments(args);

        expectations.recordStub(mockInstance, method, arguments, result);
        setupResult.setSuccess();
        return result;
    }

    private Arguments makeArguments(Object[] args) {
        return new Arguments(
                stream(args).map(EqualsMatcher::new).collect(toList())
        );
    }

    private void verifyReturnTypeNeedsToBeStubbed(ReturnType result, Method method) {
        Class<?> returnType = method.getReturnType();
        if (!returnType.equals(Void.TYPE) && returnType.isPrimitive() && result == null) {
            throw new UnableToStubPrimitiveReturnType();
        }
    }
}
