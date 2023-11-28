package nl.mvdr.adventofcode.adventofcode2019.day17;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SetAndForgetPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class SetAndForgetPart2Test extends SolverTest<SetAndForgetPart2> {

    /** Constructor. */
    public SetAndForgetPart2Test() {
        super(SetAndForgetPart2.class);
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("732985", "input-day17-2019.txt"); 
    }
}
