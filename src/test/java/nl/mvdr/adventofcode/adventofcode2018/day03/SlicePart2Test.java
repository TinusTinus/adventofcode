package nl.mvdr.adventofcode.adventofcode2018.day03;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link SlicePart2}.
 *
 * @author Martijn van de Rijdt
 */
public class SlicePart2Test extends SolverTest<SlicePart2> {
    /** Constructor. */
    public SlicePart2Test() {
        super(SlicePart2.class);
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample1() {
        assertSolution("3", "example-day03-2018-1.txt");
    }
}
