package jinsist.expectations;

public class ValueObjects {
    public static <T> boolean equal(T thisObject, Object other, EqualityTester<T> equalityTester) {
        if (thisObject == other)
            return true;
        if (other == null)
            return false;
        if (thisObject.getClass() != other.getClass())
            return false;
        @SuppressWarnings("unchecked")
        T otherAsT = (T) other;
        return equalityTester.equal(otherAsT);
    }

    public static interface EqualityTester<T> {
        boolean equal(T object);
    }
}
