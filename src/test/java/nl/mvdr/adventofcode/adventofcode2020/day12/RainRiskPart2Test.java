package nl.mvdr.adventofcode.adventofcode2020.day12;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link RainRiskPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class RainRiskPart2Test extends SolverTest<RainRiskPart2> {

    /** Constructor. */
    public RainRiskPart2Test() {
        super(RainRiskPart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        testSolution("286", "example-day12-2020.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("78883", "input-day12-2020.txt");
    }
}
