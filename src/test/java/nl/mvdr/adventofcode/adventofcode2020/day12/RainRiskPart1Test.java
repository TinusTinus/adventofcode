package nl.mvdr.adventofcode.adventofcode2020.day12;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link RainRiskPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class RainRiskPart1Test extends SolverTest<RainRiskPart1> {

    /** Constructor. */
    public RainRiskPart1Test() {
        super(RainRiskPart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("25", "example-day12-2020.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("?", "input-day12-2020.txt");
    }
}
