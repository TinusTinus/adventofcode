package nl.mvdr.adventofcode.adventofcode2019.day19;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link TractorBeamPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class TractorBeamPart2Test extends SolverTest<TractorBeamPart2> {

    /** Constructor. */
    public TractorBeamPart2Test() {
        super(TractorBeamPart2.class);
    }
    
    /** Test case based on the accepted solution. */
    @Test
    @Disabled // long-running test case
    public void testSolution() {
        assertSolution("9231141", "input-day19-2019.txt");
    }
}
