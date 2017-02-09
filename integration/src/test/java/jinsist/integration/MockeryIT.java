package jinsist.integration;

import jinsist.Mockery;
import jinsist.expectations.UnmetExpectations;
import jinsist.integration.testtypes.Collaborator;

import org.junit.Ignore;
import org.junit.Test;

public class MockeryIT {
    private Mockery mockery = new Mockery();
    private Collaborator collaborator = mockery.mock(Collaborator.class);

    @Test
    public void verifiesEmptyMockery() {
        new Mockery().verify();
    }

    @Test
    public void verifiesMockeryWithoutExpectations() {
        mockery.verify();
    }

    @Test(expected = UnmetExpectations.class)
    public void failsVerificationOnUnmetExpectations() {
        mockery.expect(collaborator).query(mock -> mock.firstMethod("some input")).returns("some output");

        mockery.verify();
    }

    @Test
    public void passesVerificationIfExpectationsAreMet() {
        mockery.expect(collaborator).query(mock -> mock.firstMethod("some input")).returns("some output");

        collaborator.firstMethod("some input");

        mockery.verify();
    }

    @Ignore("Not implemented")
    @Test(expected = RuntimeException.class)
    public void failsVerificationWithUnfinishedExpectations() {
        expectUnfinishedQuery();

        mockery.verify();
    }

    @Ignore("Not implemented")
    @Test(expected = RuntimeException.class)
    public void failsQueryWithUnfinishedPreviousQuery() {
        expectUnfinishedQuery();
        expectUnfinishedQuery();
    }

    private void expectUnfinishedQuery() {
        mockery.expect(collaborator).query(mock -> mock.firstMethod("some input"));
    }
}
