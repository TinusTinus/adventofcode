package nl.mvdr.adventofcode.adventofcode2019.day11;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SpacePolicePart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SpacePolicePart1Test extends SolverTest<SpacePolicePart1> {

    /** Constructor. */
    public SpacePolicePart1Test() {
        super(SpacePolicePart1.class);
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("2252", "input-day11-2019.txt"); 
    }
}
