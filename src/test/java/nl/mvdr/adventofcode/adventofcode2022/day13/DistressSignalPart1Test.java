package nl.mvdr.adventofcode.adventofcode2022.day13;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link DistressSignalPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class DistressSignalPart1Test extends SolverTest<DistressSignalPart1> {

    /** Constructor. */
    public DistressSignalPart1Test() {
        super(DistressSignalPart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("13", "example-day13-2022.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("6420", "input-day13-2022.txt");
    }
}
