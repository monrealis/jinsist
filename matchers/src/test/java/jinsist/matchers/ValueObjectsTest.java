package jinsist.matchers;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import jinsist.matchers.ValueObjects;
import jinsist.matchers.ValueObjects.EqualityTester;

import org.junit.Test;

import java.util.List;

public class ValueObjectsTest {
    private Pair valueObject = new Pair(1, "First");
    private EqualityTester<Pair> tester = o -> valueObject.toList().equals(o.toList());

    @Test
    public void valueObjectEqualsItself() {
        assertTrue(valueObjectsEqualsOther(valueObject));
    }

    @Test
    public void valueObjectsWithEqualFieldsAreEqual() {
        Pair otherWithEqualFields = new Pair(valueObject.getFirst(), valueObject.getSecond());
        assertTrue(valueObjectsEqualsOther(otherWithEqualFields));
    }

    @Test
    public void valueObjectsWithDifferentFieldsAreNotEqual() {
        Pair otherWithDifferentField = new Pair(valueObject.getFirst() + 1, valueObject.getSecond());
        assertFalse(valueObjectsEqualsOther(otherWithDifferentField));
    }

    @Test
    public void valueObjectsOfDifferentTypesAreNotEqual() {
        assertFalse(valueObjectsEqualsOther(null));
        assertFalse(valueObjectsEqualsOther(new Object()));
    }

    private boolean valueObjectsEqualsOther(Object other) {
        return ValueObjects.equal(valueObject, other, tester);
    }

    private static class Pair {
        private final int first;
        private final String second;

        public Pair(int first, String second) {
            this.first = first;
            this.second = second;
        }

        public int getFirst() {
            return first;
        }

        public String getSecond() {
            return second;
        }

        private List<?> toList() {
            return asList(first, second);
        }
    }
}
