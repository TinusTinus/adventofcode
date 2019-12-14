package nl.mvdr.adventofcode.adventofcode2019.day12;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link NBodyProblemPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class NBodyProblemPart1Test extends SolverTest<NBodyProblemPart1> {

    /** Constructor. */
    public NBodyProblemPart1Test() {
        super(NBodyProblemPart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample0() {
        assertSolution(new NBodyProblemPart1(10), "179", "example-day12-2019-0.txt");
    }

    /** Test case based on the second example from the puzzle text. */
    @Test
    public void testExample1() {
        assertSolution(new NBodyProblemPart1(100), "1940", "example-day12-2019-1.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("?", "input-day12-2019.txt"); 
    }
}
