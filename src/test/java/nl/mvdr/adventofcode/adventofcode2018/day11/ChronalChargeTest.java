package nl.mvdr.adventofcode.adventofcode2018.day11;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link ChronalCharge}.
 *
 * @author Martijn van de Rijdt
 */
public class ChronalChargeTest extends SolverTest<ChronalCharge> {
    /** Constructor. */
    public ChronalChargeTest() {
        super(ChronalCharge.class);
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample0() {
        assertSolution("33,45", "example-day11-2018-0.txt");
    }
    
    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample1() {
        assertSolution("21,61", "example-day11-2018-1.txt");
    }
}
