package nl.mvdr.adventofcode.adventofcode2019.day03;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link CrossedWiresPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class CrossedWiresPart2Test extends SolverTest<CrossedWiresPart2> {

    /** Constructor. */
    public CrossedWiresPart2Test() {
        super(CrossedWiresPart2.class);
    }
    
    /** Test case based on the first example from the puzzle text. */
    @Test
    public void testExample0() {
        assertSolution("30", "example-day03-2019-0.txt");
    }

    /** Test case based on the second example from the puzzle text. */
    @Test
    public void testExample1() {
        assertSolution("610", "example-day03-2019-1.txt");
    }

    /** Test case based on the third example from the puzzle text. */
    @Test
    public void testExample2() {
        assertSolution("410", "example-day03-2019-2.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("15678", "input-day03-2019.txt"); 
    }
}
