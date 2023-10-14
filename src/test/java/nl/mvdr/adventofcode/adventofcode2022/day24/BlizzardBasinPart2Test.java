package nl.mvdr.adventofcode.adventofcode2022.day24;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link BlizzardBasinPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class BlizzardBasinPart2Test extends SolverTest<BlizzardBasinPart2> {

    /** Constructor. */
    public BlizzardBasinPart2Test() {
        super(BlizzardBasinPart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample0() {
        assertSolution("30", "example-day24-2022-0.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample1() {
        assertSolution("54", "example-day24-2022-1.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("974", "input-day24-2022.txt");
    }
}