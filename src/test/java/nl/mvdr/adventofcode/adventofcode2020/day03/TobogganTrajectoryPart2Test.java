package nl.mvdr.adventofcode.adventofcode2020.day03;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link TobogganTrajectoryPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class TobogganTrajectoryPart2Test extends SolverTest<TobogganTrajectoryPart2> {

    /** Constructor. */
    public TobogganTrajectoryPart2Test() {
        super(TobogganTrajectoryPart2.class);
    }
    
    /**
     * Test case based on an example from the puzzle text.
     * 
     * In the above example, these slopes would find 2, 7, 3, 4, and 2 tree(s) respectively;
     * multiplied together, these produce the answer 336.
     */
    @Test
    public void testExample() {
        assertSolution("336", "example-day03-2020.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("5140884672", "input-day03-2020.txt");
    }
}
