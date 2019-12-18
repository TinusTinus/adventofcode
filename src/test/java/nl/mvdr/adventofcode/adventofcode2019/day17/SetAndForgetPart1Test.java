package nl.mvdr.adventofcode.adventofcode2019.day17;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SetAndForgetPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SetAndForgetPart1Test extends SolverTest<SetAndForgetPart1> {

    /** Constructor. */
    public SetAndForgetPart1Test() {
        super(SetAndForgetPart1.class);
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("5724", "input-day17-2019.txt"); 
    }
}
