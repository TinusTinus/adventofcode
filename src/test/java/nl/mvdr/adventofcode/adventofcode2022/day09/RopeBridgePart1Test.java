package nl.mvdr.adventofcode.adventofcode2022.day09;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link RopeBridgePart1}.
 *
 * @author Martijn van de Rijdt
 */
public class RopeBridgePart1Test extends SolverTest<RopeBridgePart1> {

    /** Constructor. */
    public RopeBridgePart1Test() {
        super(RopeBridgePart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("13", "example-day09-2022.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("5683", "input-day09-2022.txt");
    }
}
