package nl.mvdr.adventofcode.adventofcode2018.day16;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link ChronalClassificationPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class ChronalClassificationPart2Test extends SolverTest<ChronalClassificationPart2> {
    /** Constructor. */
    public ChronalClassificationPart2Test() {
        super(ChronalClassificationPart2.class);
    }

    /** Test case based on the accepted solution to the puzzle. */
    @Test
    public void testSolution() {
        testSolution("681", "input-day16-2018.txt");
    }
}
