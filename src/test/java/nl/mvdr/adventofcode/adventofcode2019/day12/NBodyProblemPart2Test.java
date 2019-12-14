package nl.mvdr.adventofcode.adventofcode2019.day12;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link NBodyProblemPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class NBodyProblemPart2Test extends SolverTest<NBodyProblemPart2> {

    /** Constructor. */
    public NBodyProblemPart2Test() {
        super(NBodyProblemPart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample0() {
        assertSolution("2772", "example-day12-2019-0.txt");
    }

    /** Test case based on the second example from the puzzle text. */
    @Test
    public void testExample1() {
        assertSolution("4686774924", "example-day12-2019-1.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("500903629351944", "input-day12-2019.txt"); 
    }
}
