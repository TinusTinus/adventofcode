package nl.mvdr.adventofcode.adventofcode2018.day12;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link SubterraneanSustainability}.
 *
 * @author Martijn van de Rijdt
 */
public class SubterraneanSustainabilityTest extends SolverTest<SubterraneanSustainability> {
    /** Constructor. */
    public SubterraneanSustainabilityTest() {
        super(SubterraneanSustainability.class);
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample() {
        assertSolution("325", "example-day12-2018.txt");
    }
    
    /** Test case based on the accepted solution to the puzzle. */
    @Test
    public void testSolution() {
        assertSolution("2166", "input-day12-2018.txt");
    }
}
