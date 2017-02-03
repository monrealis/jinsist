package jinsist.integration;

import jinsist.Mockery;
import jinsist.expectations.UnmetExpectations;
import jinsist.integration.testtypes.Collaborator;
import org.junit.Test;

public class MockeryIT {
    private Mockery mockery = new Mockery();
    
    @Test
    public void verifiesEmptyMockery() {
        mockery.verify();
    }

    @Test
    public void verifiesMockeryWithoutExpectations() {
        mockery.mock(Collaborator.class);

        mockery.verify();
    }

    @Test(expected = UnmetExpectations.class)
    public void failsVerificationOnUnmetExpectations() {
        Collaborator collaborator = mockery.mock(Collaborator.class);
        mockery.expect(collaborator).query(mock -> mock.firstMethod("some input")).returns("some output");

        mockery.verify();
    }

    @Test
    public void passesVerificationIfExpectationsAreMet() {
        Collaborator collaborator = mockery.mock(Collaborator.class);

        mockery.expect(collaborator).query(mock -> mock.firstMethod("some input")).returns("some output");

        collaborator.firstMethod("some input");

        mockery.verify();
    }
}
