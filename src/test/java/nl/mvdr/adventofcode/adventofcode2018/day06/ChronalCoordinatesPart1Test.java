package nl.mvdr.adventofcode.adventofcode2018.day06;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link ChronalCoordinatesPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ChronalCoordinatesPart1Test extends SolverTest<ChronalCoordinatesPart1> {
    /** Constructor. */
    public ChronalCoordinatesPart1Test() {
        super(ChronalCoordinatesPart1.class);
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample() {
        testSolution("17", "example-day06-2018.txt");
    }
}
