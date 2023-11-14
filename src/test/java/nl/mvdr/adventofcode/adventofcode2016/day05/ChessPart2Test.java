package nl.mvdr.adventofcode.adventofcode2016.day05;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link ChessPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class ChessPart2Test extends SolverTest<ChessPart2> {

    /** Constructor. */
    public ChessPart2Test() {
        super(ChessPart2.class);
    }

    /** Test case based on the example from the puzzle text. */
    @Test
    @Disabled // long-running test case
    public void testExample() {
        assertSolution("05ace8e3", "example-day05-2016.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    @Disabled // long-running test case
    public void testSolution() {
        assertSolution("863dde27", "input-day05-2016.txt");
    }
}
