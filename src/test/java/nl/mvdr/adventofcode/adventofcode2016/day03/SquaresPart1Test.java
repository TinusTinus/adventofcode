package nl.mvdr.adventofcode.adventofcode2016.day03;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SquaresPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SquaresPart1Test extends SolverTest<SquaresPart1> {

    /** Constructor. */
    public SquaresPart1Test() {
        super(SquaresPart1.class);
    }

    /** Test case based on the first example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("0", "example-day03-2016.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("1050", "input-day03-2016.txt");
    }
}
