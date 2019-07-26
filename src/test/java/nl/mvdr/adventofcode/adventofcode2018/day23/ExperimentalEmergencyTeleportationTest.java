package nl.mvdr.adventofcode.adventofcode2018.day23;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link ExperimentalEmergencyTeleportation}.
 *
 * @author Martijn van de Rijdt
 */
public class ExperimentalEmergencyTeleportationTest extends SolverTest<ExperimentalEmergencyTeleportation> {
    /** Constructor. */
    public ExperimentalEmergencyTeleportationTest() {
        super(ExperimentalEmergencyTeleportation.class);
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample() {
        assertSolution("7", "example-day23-2018.txt");
    }
    
    /** Test case based on the accepted solution to the puzzle. */
    @Test
    public void testSolution() {
        assertSolution("?", "input-day23-2018.txt");
    }
}
