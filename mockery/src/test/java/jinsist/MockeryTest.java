package jinsist;

import static org.junit.Assert.assertNotSame;

import org.junit.Test;

public class MockeryTest {
    private Mockery mockery = new Mockery();

    @Test(expected = RuntimeException.class)
    public void expectTakesOnlyMocks() {
        Object notMock = new Object();

        mockery.expect(notMock);
    }

    @Test(expected = IllegalArgumentException.class)
    public void expectTakesOnlyMocksCreatedInThisMockery() {
        Collaborator mock = mockery.mock(Collaborator.class);
        Collaborator notMock = clone(mock);
        assertNotSame(mock, notMock);

        mockery.expect(notMock);
    }

    private Collaborator clone(Collaborator collaborator) {
        try {
            return collaborator.getClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static class Collaborator {

    }
}
