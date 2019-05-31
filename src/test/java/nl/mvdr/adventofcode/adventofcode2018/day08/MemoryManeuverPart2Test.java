package nl.mvdr.adventofcode.adventofcode2018.day08;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link MemoryManeuverPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class MemoryManeuverPart2Test extends SolverTest<MemoryManeuverPart2> {
    /** Constructor. */
    public MemoryManeuverPart2Test() {
        super(MemoryManeuverPart2.class);
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample() {
        assertSolution("66", "example-day08-2018.txt");
    }
}
