package nl.mvdr.adventofcode.adventofcode2018.day19;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link GoWithTheFlowPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ChronalClassificationPart1Test extends SolverTest<GoWithTheFlowPart1> {
    /** Constructor. */
    public ChronalClassificationPart1Test() {
        super(GoWithTheFlowPart1.class);
    }

    /** Test case based on the example from the puzzle. */
    @Test
    public void testExample() {
        assertSolution("7", "example-day19-2018.txt");
    }
    
    /** Test case based on the accepted solution to the puzzle. */
    @Test
    public void testSolution() {
        assertSolution("2304", "input-day19-2018.txt");
    }
}
