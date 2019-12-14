package nl.mvdr.adventofcode.adventofcode2019.day14;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SpaceStoichiometryPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SpaceStoichiometryPart1Test extends SolverTest<SpaceStoichiometryPart1> {

    /** Constructor. */
    public SpaceStoichiometryPart1Test() {
        super(SpaceStoichiometryPart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample0() {
        assertSolution("31", "example-day14-2019-0.txt");
    }

    /** Test case based on the second example from the puzzle text. */
    @Test
    public void testExample1() {
        assertSolution("165", "example-day14-2019-1.txt");
    }

    /** Test case based on the third example from the puzzle text. */
    @Test
    public void testExample2() {
        assertSolution("13312", "example-day14-2019-2.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample3() {
        assertSolution("180697", "example-day14-2019-3.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample4() {
        assertSolution("2210736", "example-day14-2019-4.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("?", "input-day14-2019.txt"); 
    }
}
