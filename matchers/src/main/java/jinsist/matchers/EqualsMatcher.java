package jinsist.matchers;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EqualsMatcher that = (EqualsMatcher) o;

        return value != null ? value.equals(that.value) : that.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
