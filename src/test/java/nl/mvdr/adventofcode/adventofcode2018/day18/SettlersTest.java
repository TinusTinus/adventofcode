package nl.mvdr.adventofcode.adventofcode2018.day18;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit tests for {@link Settlers}.
 *
 * @author Martijn van de Rijdt
 */
public class SettlersTest extends SolverTest<Settlers> {
    /** Constructor. */
    public SettlersTest() {
        super(Settlers.class);
    }
    
    /** Test case based on the example from the puzzle. */
    @Test
    public void testExample() {
        assertSolution("1147", "example-day18-2018.txt");
    }
}
