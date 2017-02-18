package jinsist.matchers;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import jinsist.matchers.ValueObjects;
import jinsist.matchers.ValueObjects.EqualityTester;

import org.junit.Test;

import java.util.List;

public class ValueObjectsTest {
    private Pair pair = new Pair(1, "First");
    private EqualityTester<Pair> tester = o -> pair.toList().equals(o.toList());

    @Test
    public void instanceEqualsItself() {
        assertTrue(isPairEqualsAnother(pair));
    }

    @Test
    public void equalInstancesAreEqual() {
        Pair otherEqual = new Pair(pair.getFirst(), pair.getSecond());
        assertTrue(isPairEqualsAnother(otherEqual));
    }

    @Test
    public void ifAnyComponentDiffersThenNotEqual() {
        Pair typeDiffers = new Pair(pair.getFirst() + 1, pair.getSecond());
        assertFalse(isPairEqualsAnother(typeDiffers));
    }

    @Test
    public void ifTypesDifferThenNotEqual() {
        assertFalse(isPairEqualsAnother(null));
        assertFalse(isPairEqualsAnother(new Object()));
    }

    private boolean isPairEqualsAnother(Object other) {
        return ValueObjects.equal(pair, other, tester);
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
