package nl.mvdr.adventofcode.adventofcode2021.day02;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link DivePart2}.
 *
 * @author Martijn van de Rijdt
 */
public class DivePart2Test extends SolverTest<DivePart2> {

    /** Constructor. */
    public DivePart2Test() {
        super(DivePart2.class);
    }

    /** Test case based on the example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("900", "example-day02-2021.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("?", "input-day02-2021.txt"); // 1648020 is too low! (maybe an overflow?)
    }
}
