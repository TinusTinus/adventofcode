package nl.mvdr.adventofcode.adventofcode2022.day17;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link PyroclasticFlowPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class PyroclasticFlowPart1Test extends SolverTest<PyroclasticFlowPart1> {

    /** Constructor. */
    public PyroclasticFlowPart1Test() {
        super(PyroclasticFlowPart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        testSolution("3068", "example-day17-2022.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("3069", "input-day17-2022.txt");
    }
}
