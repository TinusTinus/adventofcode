package nl.mvdr.adventofcode.adventofcode2018.day05;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link AlchemicalReduction}.
 *
 * @author Martijn van de Rijdt
 */
public class AlchemicalReductionTest extends SolverTest<AlchemicalReduction> {
    /** Constructor. */
    public AlchemicalReductionTest() {
        super(AlchemicalReduction.class);
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample0() {
        assertSolution("10", "example-day05-2018-0.txt");
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample1() {
        assertSolution("10", "example-day05-2018-1.txt");
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample2() {
        assertSolution("10", "example-day05-2018-2.txt");
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample3() {
        assertSolution("10", "example-day05-2018-3.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("9296", "input-day05-2018.txt");
    }
}
