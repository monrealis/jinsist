package jinsist.matchers;

import static java.util.Arrays.asList;

import java.util.List;

public class EqualsMatcher implements ArgumentMatcher {
    private Object value;

    public EqualsMatcher(Object value) {
        this.value = value;
    }

    @Override
    public boolean matches(Object argument) {
        boolean isValueNull = value == null;
        boolean isArgumentNull = argument == null;

        return isArgumentNull && isValueNull || !isValueNull && value.equals(argument);
    }

    @Override
    public String toString() {
        return "EqualsMatcher " + value;
    }

    @Override
    public boolean equals(Object other) {
        return ValueObjects.equal(this, other, o -> toList().equals(o.toList()));
    }

    @Override
    public int hashCode() {
        return toList().hashCode();
    }

    private List<?> toList() {
        return asList(value);
    }
}
