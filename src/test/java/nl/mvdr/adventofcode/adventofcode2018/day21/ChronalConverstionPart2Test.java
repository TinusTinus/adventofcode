package nl.mvdr.adventofcode.adventofcode2018.day21;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link ChronalConversionPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class ChronalConverstionPart2Test extends SolverTest<ChronalConversionPart2> {
    /** Constructor. */
    public ChronalConverstionPart2Test() {
        super(ChronalConversionPart2.class);
    }

    /** Test case based on the accepted solution to the puzzle. */
    @Test
    @Disabled // This test case is very slow
    public void testSolution() {
        assertSolution("6577657", "input-day21-2018.txt");
    }
}
