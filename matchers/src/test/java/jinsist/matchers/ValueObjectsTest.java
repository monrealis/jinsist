package jinsist.matchers;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import jinsist.matchers.ValueObjects;
import jinsist.matchers.ValueObjects.EqualityTester;

import org.junit.Test;

import java.util.List;

public class ValueObjectsTest {
    private Pair instance = new Pair(1, "First");
    private EqualityTester<Pair> tester = o -> instance.toList().equals(o.toList());

    @Test
    public void instanceEqualsItself() {
        assertTrue(expectEqual(instance));
    }

    @Test
    public void equalInstancesAreEqual() {
        Pair otherEqual = new Pair(instance.getFirst(), instance.getSecond());
        assertTrue(expectEqual(otherEqual));
    }

    @Test
    public void ifAnyComponentDiffersThenNotEqual() {
        Pair typeDiffers = new Pair(instance.getFirst() + 1, instance.getSecond());
        assertFalse(expectEqual(typeDiffers));
    }

    @Test
    public void ifTypesDifferThenNotEqual() {
        assertFalse(expectEqual(null));
        assertFalse(expectEqual(new Object()));
    }

    private boolean expectEqual(Object other) {
        return ValueObjects.equal(instance, other, tester);
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
