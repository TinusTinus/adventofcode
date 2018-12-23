package nl.mvdr.adventofcode.adventofcode2018.day18;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit tests for {@link SettlersPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class SettlersPart2Test extends SolverTest<SettlersPart2> {
    /** Constructor. */
    public SettlersPart2Test() {
        super(SettlersPart2.class);
    }
    
    /** Test case based on the accepted solution to the puzzle. */
    @Test
    public void testSolution() {
        assertSolution("205296", "input-day18-2018.txt");
    }
}
