package nl.mvdr.adventofcode.adventofcode2019.day01;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link RocketEquationPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class RocketEquationPart2Test extends SolverTest<RocketEquationPart2> {

    /** Constructor. */
    public RocketEquationPart2Test() {
        super(RocketEquationPart2.class);
    }

    /** Test case based on the first example from the puzzle text. */
    @Test
    public void testExample0() {
        assertSolution("2", "example-day01-2019-0.txt");
    }

    /** Test case based on the second example from the puzzle text. */
    @Test
    public void testExample1() {
        assertSolution("2", "example-day01-2019-1.txt");
    }

    /** Test case based on the third example from the puzzle text. */
    @Test
    public void testExample2() {
        assertSolution("966", "example-day01-2019-2.txt");
    }

    /** Test case based on the second example from the puzzle text. */
    @Test
    public void testExample3() {
        assertSolution("50346", "example-day01-2019-3.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("?", "input-day01-2019.txt"); // 4729333 is too high!
    }
}
