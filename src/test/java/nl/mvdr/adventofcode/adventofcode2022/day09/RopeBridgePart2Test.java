package nl.mvdr.adventofcode.adventofcode2022.day09;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link RopeBridgePart2}.
 *
 * @author Martijn van de Rijdt
 */
public class RopeBridgePart2Test extends SolverTest<RopeBridgePart2> {

    /** Constructor. */
    public RopeBridgePart2Test() {
        super(RopeBridgePart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("36", "example-day09-2022.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("?", "input-day09-2022.txt");
    }
}
