package nl.mvdr.adventofcode.adventofcode2022.day24;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link BlizzardBasinPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class BlizzardBasinPart1Test extends SolverTest<BlizzardBasinPart1> {

    /** Constructor. */
    public BlizzardBasinPart1Test() {
        super(BlizzardBasinPart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample0() {
        assertSolution("10", "example-day24-2022-0.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample1() {
        assertSolution("18", "example-day24-2022-1.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    @Disabled // long-running test case
    public void testSolution() {
        assertSolution("322", "input-day24-2022.txt");
    }
}
