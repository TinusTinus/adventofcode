package nl.mvdr.adventofcode.adventofcode2018.day21;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link ChronalConversionPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ChronalConverstionPart1Test extends SolverTest<ChronalConversionPart1> {
    /** Constructor. */
    public ChronalConverstionPart1Test() {
        super(ChronalConversionPart1.class);
    }

    /** Test case based on the accepted solution to the puzzle. */
    @Test
    public void testSolution() {
        // The first time the instruction on line 30 is executed, the register values are:
        // [<input>, 0, 1, 30, 11840402, 1]
        // So the answer is 11840402.
        testSolution("11840402", "input-day21-2018.txt");
    }
}
