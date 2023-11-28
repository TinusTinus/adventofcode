package nl.mvdr.adventofcode.adventofcode2022.day23;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link UnstableDiffusionPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class UnstableDiffusionPart2Test extends SolverTest<UnstableDiffusionPart2> {

    /** Constructor. */
    public UnstableDiffusionPart2Test() {
        super(UnstableDiffusionPart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample0() {
        testSolution("4", "example-day23-2022-0.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample1() {
        testSolution("20", "example-day23-2022-1.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    @Disabled // long-running test case
    public void testSolution() {
        testSolution("992", "input-day23-2022.txt");
    }
}
