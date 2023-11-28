package nl.mvdr.adventofcode.adventofcode2018.day06;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link ChronalCoordinatesPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class ChronalCoordinatesPart2Test extends SolverTest<ChronalCoordinatesPart2> {
    /** Constructor. */
    public ChronalCoordinatesPart2Test() {
        super(ChronalCoordinatesPart2.class);
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample() {
        testSolution("16", "example-day06-2018.txt");
    }
}
