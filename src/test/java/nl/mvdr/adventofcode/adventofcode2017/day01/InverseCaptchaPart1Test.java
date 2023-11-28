package nl.mvdr.adventofcode.adventofcode2017.day01;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link InverseCaptchaPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class InverseCaptchaPart1Test extends SolverTest<InverseCaptchaPart1> {

    /** Constructor. */
    public InverseCaptchaPart1Test() {
        super(InverseCaptchaPart1.class);
    }

    /** Test case based on the first example from the puzzle text. */
    @Test
    public void testExample0() {
        testSolution("3", "example-day01-2017-0.txt");
    }

    /** Test case based on the second example from the puzzle text. */
    @Test
    public void testExample1() {
        testSolution("4", "example-day01-2017-1.txt");
    }

    /** Test case based on the third example from the puzzle text. */
    @Test
    public void testExample2() {
        testSolution("0", "example-day01-2017-2.txt");
    }

    /** Test case based on the second example from the puzzle text. */
    @Test
    public void testExample3() {
        testSolution("9", "example-day01-2017-3.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("1251", "input-day01-2017.txt");
    }
}
