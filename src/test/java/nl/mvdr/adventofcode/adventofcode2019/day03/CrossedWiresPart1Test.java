package nl.mvdr.adventofcode.adventofcode2019.day03;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link CrossedWiresPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class CrossedWiresPart1Test extends SolverTest<CrossedWiresPart1> {

    /** Constructor. */
    public CrossedWiresPart1Test() {
        super(CrossedWiresPart1.class);
    }
    
    /** Test case based on the first example from the puzzle text. */
    @Test
    public void testExample0() {
        testSolution("6", "example-day03-2019-0.txt");
    }

    /** Test case based on the second example from the puzzle text. */
    @Test
    public void testExample1() {
        testSolution("159", "example-day03-2019-1.txt");
    }

    /** Test case based on the third example from the puzzle text. */
    @Test
    public void testExample2() {
        testSolution("135", "example-day03-2019-2.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("399", "input-day03-2019.txt"); 
    }
}
