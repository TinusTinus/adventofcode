package nl.mvdr.adventofcode.adventofcode2017.day01;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link InverseCaptchaPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class InverseCaptchaPart2Test extends SolverTest<InverseCaptchaPart2> {

    /** Constructor. */
    public InverseCaptchaPart2Test() {
        super(InverseCaptchaPart2.class);
    }

    /** Test case based on the first example from the puzzle text. */
    @Test
    public void testExample4() {
        assertSolution("6", "example-day01-2017-4.txt");
    }

    /** Test case based on the second example from the puzzle text. */
    @Test
    public void testExample5() {
        assertSolution("0", "example-day01-2017-5.txt");
    }

    /** Test case based on the third example from the puzzle text. */
    @Test
    public void testExample6() {
        assertSolution("4", "example-day01-2017-6.txt");
    }

    /** Test case based on the second example from the puzzle text. */
    @Test
    public void testExample7() {
        assertSolution("12", "example-day01-2017-7.txt");
    }
    
    /** Test case based on the second example from the puzzle text. */
    @Test
    public void testExample8() {
        assertSolution("4", "example-day01-2017-8.txt");
    }
    
    /** Test case based on the accepted slution. */
    @Test
    public void testSolution() {
        assertSolution("1244", "input-day01-2017.txt");
    }
}
