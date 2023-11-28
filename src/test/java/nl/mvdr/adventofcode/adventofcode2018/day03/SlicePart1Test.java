package nl.mvdr.adventofcode.adventofcode2018.day03;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link SlicePart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SlicePart1Test extends SolverTest<SlicePart1> {
    /** Constructor. */
    public SlicePart1Test() {
        super(SlicePart1.class);
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample0() {
        testSolution("0", "example-day03-2018-0.txt");
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample1() {
        testSolution("4", "example-day03-2018-1.txt");
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample2() {
        testSolution("4", "example-day03-2018-2.txt");
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample3() {
        testSolution("0", "example-day03-2018-3.txt");
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample4() {
        testSolution("0", "example-day03-2018-4.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("124850", "input-day03-2018.txt");
    }
}
