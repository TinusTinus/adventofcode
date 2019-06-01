package nl.mvdr.adventofcode.adventofcode2018.day12;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link SubterraneanSustainabilityPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SubterraneanSustainabilityPart1Test extends SolverTest<SubterraneanSustainabilityPart1> {
    /** Constructor. */
    public SubterraneanSustainabilityPart1Test() {
        super(SubterraneanSustainabilityPart1.class);
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample0() {
        assertSolution("325", "example-day12-2018.txt");
    }
}
