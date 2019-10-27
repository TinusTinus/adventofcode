package nl.mvdr.adventofcode.adventofcode2017.day25;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link HaltingProblem}.
 *
 * @author Martijn van de Rijdt
 */
public class HaltingProblemTest extends SolverTest<HaltingProblem> {

    /** Constructor. */
    public HaltingProblemTest() {
        super(HaltingProblem.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("3", "example-day25-2017.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("?", "input-day25-2017.txt");
    }
}
