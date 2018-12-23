package nl.mvdr.adventofcode.adventofcode2018.day03;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link Slice}.
 *
 * @author Martijn van de Rijdt
 */
public class SliceTest extends SolverTest<Slice> {
    /** Constructor. */
    public SliceTest() {
        super(Slice.class);
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample0() {
        assertSolution("0", "example-day03-2018-0.txt");
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample1() {
        assertSolution("4", "example-day03-2018-1.txt");
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample2() {
        assertSolution("4", "example-day03-2018-2.txt");
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample3() {
        assertSolution("0", "example-day03-2018-3.txt");
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample4() {
        assertSolution("0", "example-day03-2018-4.txt");
    }
}
