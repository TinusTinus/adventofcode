package nl.mvdr.adventofcode.adventofcode2018.day06;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link ChronalCoordinates}.
 *
 * @author Martijn van de Rijdt
 */
public class ChronalCoordinatesTest extends SolverTest<ChronalCoordinates> {
    /** Constructor. */
    public ChronalCoordinatesTest() {
        super(ChronalCoordinates.class);
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample() {
        assertSolution("17", "example-day06-2018.txt");
    }
}
