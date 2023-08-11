package nl.mvdr.adventofcode.adventofcode2022.day18;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link BoilingBouldersPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class BoilingBouldersPart1Test extends SolverTest<BoilingBouldersPart1> {

    /** Constructor. */
    public BoilingBouldersPart1Test() {
        super(BoilingBouldersPart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample0() {
        assertSolution("10", "example-day18-2022-0.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample1() {
        assertSolution("64", "example-day18-2022-1.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("4460", "input-day18-2022.txt");
    }
}
