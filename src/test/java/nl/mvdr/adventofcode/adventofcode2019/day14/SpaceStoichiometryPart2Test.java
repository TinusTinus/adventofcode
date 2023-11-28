package nl.mvdr.adventofcode.adventofcode2019.day14;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SpaceStoichiometryPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class SpaceStoichiometryPart2Test extends SolverTest<SpaceStoichiometryPart2> {

    /** Constructor. */
    public SpaceStoichiometryPart2Test() {
        super(SpaceStoichiometryPart2.class);
    }
    
    /** Test case based on the third example from the puzzle text. */
    @Test
    public void testExample2() {
        testSolution("82892753", "example-day14-2019-2.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample3() {
        testSolution("5586022", "example-day14-2019-3.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample4() {
        testSolution("460664", "example-day14-2019-4.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("5194174", "input-day14-2019.txt"); 
    }
}
