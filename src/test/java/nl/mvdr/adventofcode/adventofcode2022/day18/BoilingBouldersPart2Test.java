package nl.mvdr.adventofcode.adventofcode2022.day18;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link BoilingBouldersPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class BoilingBouldersPart2Test extends SolverTest<BoilingBouldersPart2> {

    /** Constructor. */
    public BoilingBouldersPart2Test() {
        super(BoilingBouldersPart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample0() {
        testSolution("10", "example-day18-2022-0.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample1() {
        testSolution("58", "example-day18-2022-1.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("2498", "input-day18-2022.txt");
    }
}
