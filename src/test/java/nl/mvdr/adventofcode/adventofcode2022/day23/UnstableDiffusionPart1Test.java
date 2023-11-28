package nl.mvdr.adventofcode.adventofcode2022.day23;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link UnstableDiffusionPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class UnstableDiffusionPart1Test extends SolverTest<UnstableDiffusionPart1> {

    /** Constructor. */
    public UnstableDiffusionPart1Test() {
        super(UnstableDiffusionPart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample0() {
        testSolution("25", "example-day23-2022-0.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample1() {
        testSolution("110", "example-day23-2022-1.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("4254", "input-day23-2022.txt");
    }
}
