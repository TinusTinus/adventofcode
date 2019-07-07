package nl.mvdr.adventofcode.adventofcode2018.day17;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link ReservoirResearch}.
 *
 * @author Martijn van de Rijdt
 */
public class ReservoirResearchTest extends SolverTest<ReservoirResearch> {
    /** Constructor. */
    public ReservoirResearchTest() {
        super(ReservoirResearch.class);
    }

    /** Test case based on the example from the puzzle. */
    @Test
    public void testExample() {
        assertSolution("57", "example-day17-2018.txt");
    }
    
    /** Test case based on the accepted solution to the puzzle. */
    @Test
    public void testSolution() {
        assertSolution("?", "input-day17-2018.txt");
    }
}
