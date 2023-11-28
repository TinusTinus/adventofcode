package nl.mvdr.adventofcode.adventofcode2018.day05;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link AlchemicalReductionPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class AlchemicalReductionPart2Test extends SolverTest<AlchemicalReductionPart2> {
    /** Constructor. */
    public AlchemicalReductionPart2Test() {
        super(AlchemicalReductionPart2.class);
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample() {
        testSolution("4", "example-day05-2018-0.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    @Disabled // This test case is very slow
    public void testSolution() {
        testSolution("5534", "input-day05-2018.txt");
    }

}
