package nl.mvdr.adventofcode.adventofcode2019.day19;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link TractorBeamPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class TractorBeamPart1Test extends SolverTest<TractorBeamPart1> {

    /** Constructor. */
    public TractorBeamPart1Test() {
        super(TractorBeamPart1.class);
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("186", "input-day19-2019.txt"); 
    }
}
