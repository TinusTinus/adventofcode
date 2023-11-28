package nl.mvdr.adventofcode.adventofcode2018.day16;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link ChronalClassificationPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ChronalClassificationPart1Test extends SolverTest<ChronalClassificationPart1> {
    /** Constructor. */
    public ChronalClassificationPart1Test() {
        super(ChronalClassificationPart1.class);
    }

    /** Test case based on the example from the puzzle. */
    @Test
    public void testExample() {
        testSolution("1", "example-day16-2018.txt");
    }
    
    /** Test case based on the accepted solution to the puzzle. */
    @Test
    public void testSolution() {
        testSolution("646", "input-day16-2018.txt");
    }
}
