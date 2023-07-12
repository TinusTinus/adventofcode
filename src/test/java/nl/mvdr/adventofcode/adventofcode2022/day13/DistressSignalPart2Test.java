package nl.mvdr.adventofcode.adventofcode2022.day13;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link DistressSignalPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class DistressSignalPart2Test extends SolverTest<DistressSignalPart2> {

    /** Constructor. */
    public DistressSignalPart2Test() {
        super(DistressSignalPart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("140", "example-day13-2022.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("22000", "input-day13-2022.txt");
    }
}
