package jinsist.expectations;

import java.util.function.Supplier;

public class ValueObjects {
    public static boolean equal(Object thisObject, Object other, Supplier<Boolean> equalTesterIfTypesMatch) {
        if (thisObject == other)
            return true;
        if (other == null)
            return false;
        if (thisObject.getClass() != other.getClass())
            return false;
        return equalTesterIfTypesMatch.get();
    }
}
