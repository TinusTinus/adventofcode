package nl.mvdr.adventofcode.adventofcode2018.day05;

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
        assertSolution("4", "example-day05-2018-0.txt");
    }

}
