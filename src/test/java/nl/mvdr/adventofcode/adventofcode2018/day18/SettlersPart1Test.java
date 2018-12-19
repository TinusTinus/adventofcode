package nl.mvdr.adventofcode.adventofcode2018.day18;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit tests for {@link SettlersPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SettlersPart1Test extends SolverTest<SettlersPart1> {
    /** Constructor. */
    public SettlersPart1Test() {
        super(SettlersPart1.class);
    }
    
    /** Test case based on the example from the puzzle. */
    @Test
    public void testExample() {
        assertSolution("1147", "example-day18-2018.txt");
    }
    
    /** Test case based on the accepted solution to the puzzle. */
    @Test
    public void testSolution() {
        assertSolution("531417", "input-day18-2018.txt");
    }
}
