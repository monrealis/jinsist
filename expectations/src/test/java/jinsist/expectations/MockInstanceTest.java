package jinsist.expectations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class MockInstanceTest {
    private MockInstance<String> instance = new MockInstance<String>(String.class, "First");

    @Test
    public void instanceEqualsItself() {
        assertEquals(instance, instance);
    }

    @Test
    public void equalInstancesAreEqual() {
        MockInstance<String> otherEqual = new MockInstance<>(instance.getMockClass(), instance.getInstance());
        assertEquals(instance, otherEqual);
        assertEquals(instance.hashCode(), otherEqual.hashCode());
    }

    @Test
    public void ifMockClassDiffersThenNotEqual() {
        MockInstance<Object> typeDiffers = new MockInstance<>(Object.class, instance.getInstance());
        assertNotEquals(instance, typeDiffers);
    }

    @Test
    public void ifValueDiffersThenNotEqual() {
        MockInstance<String> typeDiffers = new MockInstance<>(instance.getMockClass(), "Other");
        assertNotEquals(instance, typeDiffers);
    }

    @Test
    public void ifTypesDifferThenNotEqual() {
        assertNotEquals(instance, null);
        assertNotEquals(instance, instance.getInstance());
    }
}
