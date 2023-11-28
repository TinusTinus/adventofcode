package nl.mvdr.adventofcode.adventofcode2021.day02;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link DivePart1}.
 *
 * @author Martijn van de Rijdt
 */
public class DivePart1Test extends SolverTest<DivePart1> {

    /** Constructor. */
    public DivePart1Test() {
        super(DivePart1.class);
    }

    /** Test case based on the example from the puzzle text. */
    @Test
    public void testExample() {
        testSolution("150", "example-day02-2021.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("1648020", "input-day02-2021.txt");
    }
}
