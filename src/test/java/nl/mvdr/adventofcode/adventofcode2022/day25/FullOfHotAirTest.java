package nl.mvdr.adventofcode.adventofcode2022.day25;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link FullOfHotAir}.
 *
 * @author Martijn van de Rijdt
 */
public class FullOfHotAirTest extends SolverTest<FullOfHotAir> {

    /** Constructor. */
    public FullOfHotAirTest() {
        super(FullOfHotAir.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("2=-1=0", "example-day25-2022.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("?", "input-day25-2022.txt");
    }
}
