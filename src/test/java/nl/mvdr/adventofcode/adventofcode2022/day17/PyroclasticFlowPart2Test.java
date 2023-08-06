package nl.mvdr.adventofcode.adventofcode2022.day17;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link PyroclasticFlowPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class PyroclasticFlowPart2Test extends SolverTest<PyroclasticFlowPart2> {

    /** Constructor. */
    public PyroclasticFlowPart2Test() {
        super(PyroclasticFlowPart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("1514285714288", "example-day17-2022.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("?", "input-day17-2022.txt");
    }
}
