package nl.mvdr.adventofcode.adventofcode2018.day13;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link MineCartMadnessPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class MineCartMadnessPart2Test extends SolverTest<MineCartMadnessPart2> {
    /** Constructor. */
    public MineCartMadnessPart2Test() {
        super(MineCartMadnessPart2.class);
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample() {
        assertSolution("6,4", "example-day13-2018-4.txt");
    }
    
    /** Test case based on the accepted solution to the puzzle. */
    @Test
    public void testSolution() {
        assertSolution("????", "input-day13-2018.txt");
    }
}
