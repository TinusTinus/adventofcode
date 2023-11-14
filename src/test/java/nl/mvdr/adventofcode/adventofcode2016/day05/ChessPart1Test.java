package nl.mvdr.adventofcode.adventofcode2016.day05;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link ChessPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ChessPart1Test extends SolverTest<ChessPart1> {

    /** Constructor. */
    public ChessPart1Test() {
        super(ChessPart1.class);
    }

    /** Test case based on the example from the puzzle text. */
    @Test
    @Disabled // long-running test case
    public void testExample() {
        assertSolution("18f47a30", "example-day05-2016.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    @Disabled // long-running test case
    public void testSolution() {
        assertSolution("f97c354d", "input-day05-2016.txt");
    }
}
