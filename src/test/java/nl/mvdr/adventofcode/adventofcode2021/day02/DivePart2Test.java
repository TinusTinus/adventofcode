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
        testSolution("900", "example-day02-2021.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("1759818555", "input-day02-2021.txt");
    }
}
