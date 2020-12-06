package nl.mvdr.adventofcode.adventofcode2020.day04;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link PassportProcessingPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class PassportProcessingPart2Test extends SolverTest<PassportProcessingPart2> {

    /** Constructor. */
    public PassportProcessingPart2Test() {
        super(PassportProcessingPart2.class);
    }
    
    /**
     * Test case based on an example from the puzzle text.
     */
    @Test
    public void testExample() {
        assertSolution("4", "example-day04-2020-1.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("188", "input-day04-2020.txt");
    }
}
