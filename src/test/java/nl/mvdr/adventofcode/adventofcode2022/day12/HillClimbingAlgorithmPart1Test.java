package nl.mvdr.adventofcode.adventofcode2022.day12;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link HillClimbingAlgorithmPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class HillClimbingAlgorithmPart1Test extends SolverTest<HillClimbingAlgorithmPart1> {

    /** Constructor. */
    public HillClimbingAlgorithmPart1Test() {
        super(HillClimbingAlgorithmPart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        testSolution("31", "example-day12-2022.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("484", "input-day12-2022.txt");
    }
}
