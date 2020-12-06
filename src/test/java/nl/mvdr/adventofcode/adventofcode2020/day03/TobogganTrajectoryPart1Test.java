package nl.mvdr.adventofcode.adventofcode2020.day03;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link TobogganTrajectoryPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class TobogganTrajectoryPart1Test extends SolverTest<TobogganTrajectoryPart1> {

    /** Constructor. */
    public TobogganTrajectoryPart1Test() {
        super(TobogganTrajectoryPart1.class);
    }
    
    /**
     * Test case based on an example from the puzzle text.
     * 
     * In this example, traversing the map using this slope would cause you to encounter 7 trees.
     */
    @Test
    public void testExample() {
        assertSolution("7", "example-day03-2020.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("198", "input-day03-2020.txt"); 
    }
}
