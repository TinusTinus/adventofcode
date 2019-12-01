package nl.mvdr.adventofcode.adventofcode2019.day01;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link RocketEquationPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class RocketEquationPart1Test extends SolverTest<RocketEquationPart1> {

    /** Constructor. */
    public RocketEquationPart1Test() {
        super(RocketEquationPart1.class);
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
        assertSolution("654", "example-day01-2019-2.txt");
    }

    /** Test case based on the second example from the puzzle text. */
    @Test
    public void testExample3() {
        assertSolution("33583", "example-day01-2019-3.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("3152919", "input-day01-2019.txt");
    }
}
