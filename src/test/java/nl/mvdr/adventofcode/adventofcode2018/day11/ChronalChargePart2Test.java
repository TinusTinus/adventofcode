package nl.mvdr.adventofcode.adventofcode2018.day11;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link ChronalChargePart2}.
 *
 * @author Martijn van de Rijdt
 */
@Disabled // These test cases take a long time
public class ChronalChargePart2Test extends SolverTest<ChronalChargePart2> {
    /** Constructor. */
    public ChronalChargePart2Test() {
        super(ChronalChargePart2.class);
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample0() {
        testSolution("90,269,16", "example-day11-2018-0.txt");
    }
    
    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample1() {
        testSolution("232,251,12", "example-day11-2018-1.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("236,175,11", "input-day11-2018.txt");
    }
}
