package nl.mvdr.adventofcode.adventofcode2018.day19;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link GoWithTheFlowPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class GoWithTheFlowPart2Test extends SolverTest<GoWithTheFlowPart2> {
    /** Constructor. */
    public GoWithTheFlowPart2Test() {
        super(GoWithTheFlowPart2.class);
    }

    /** Test case based on the accepted solution to the puzzle. */
    @Test
    public void testSolution() {
        assertSolution("28137600", "input-day19-2018.txt");
    }
}
