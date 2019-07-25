package nl.mvdr.adventofcode.adventofcode2018.day22;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link ModeMaze}.
 *
 * @author Martijn van de Rijdt
 */
public class ModeMazeTest extends SolverTest<ModeMaze> {
    /** Constructor. */
    public ModeMazeTest() {
        super(ModeMaze.class);
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample() {
        assertSolution("114", "example-day22-2018.txt");
    }
    
    /** Test case based on the accepted solution to the puzzle. */
    @Test
    public void testSolution() {
        assertSolution("9899", "input-day22-2018.txt");
    }
}
