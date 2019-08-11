package nl.mvdr.adventofcode.adventofcode2018.day23;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link ExperimentalEmergencyTeleportationPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class ExperimentalEmergencyTeleportationPart2Test extends SolverTest<ExperimentalEmergencyTeleportationPart2> {
    /** Constructor. */
    public ExperimentalEmergencyTeleportationPart2Test() {
        super(ExperimentalEmergencyTeleportationPart2.class);
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample() {
        assertSolution("36", "example-day23-2018-1.txt");
    }
    
    /** Test case based on the accepted solution to the puzzle. */
    @Test
    @Disabled // This test case takes a long time
    public void testSolution() {
        assertSolution("124623002", "input-day23-2018.txt");
    }
}
