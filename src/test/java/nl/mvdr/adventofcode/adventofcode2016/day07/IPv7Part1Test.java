package nl.mvdr.adventofcode.adventofcode2016.day07;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link IPv7Part1}.
 *
 * @author Martijn van de Rijdt
 */
public class IPv7Part1Test extends SolverTest<IPv7Part1> {

    /** Constructor. */
    public IPv7Part1Test() {
        super(IPv7Part1.class);
    }

    /** Test case based on the example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("2", "example-day07-2016-0.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("105", "input-day07-2016.txt");
    }
}
