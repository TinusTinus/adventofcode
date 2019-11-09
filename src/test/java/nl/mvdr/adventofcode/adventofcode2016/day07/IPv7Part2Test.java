package nl.mvdr.adventofcode.adventofcode2016.day07;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link IPv7Part2}.
 *
 * @author Martijn van de Rijdt
 */
public class IPv7Part2Test extends SolverTest<IPv7Part2> {

    /** Constructor. */
    public IPv7Part2Test() {
        super(IPv7Part2.class);
    }

    /** Test case based on the example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("3", "example-day07-2016-1.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("258", "input-day07-2016.txt");
    }
}
