package nl.mvdr.adventofcode.adventofcode2022.day12;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link HillClimbingAlgorithmPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class HillClimbingAlgorithmPart2Test extends SolverTest<HillClimbingAlgorithmPart2> {

    /** Constructor. */
    public HillClimbingAlgorithmPart2Test() {
        super(HillClimbingAlgorithmPart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        testSolution("29", "example-day12-2022.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("478", "input-day12-2022.txt");
    }
}
