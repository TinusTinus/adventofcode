package nl.mvdr.adventofcode.adventofcode2018.day21;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link ChronalConversion}.
 *
 * @author Martijn van de Rijdt
 */
public class ChronalConverstionTest extends SolverTest<ChronalConversion> {
    /** Constructor. */
    public ChronalConverstionTest() {
        super(ChronalConversion.class);
    }

    /** Test case based on the accepted solution to the puzzle. */
    @Test
    public void testSolution() {
        // The first time the instruction on line 30 is executed, the register values are:
        // [<input>, 0, 1, 30, 11840402, 1]
        // So the answer is 11840402.
        assertSolution("11840402", "input-day21-2018.txt");
    }
}
