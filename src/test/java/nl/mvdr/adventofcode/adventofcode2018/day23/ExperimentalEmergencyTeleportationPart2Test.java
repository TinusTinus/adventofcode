package nl.mvdr.adventofcode.adventofcode2018.day23;

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
    public void testSolution() {
        // Note: a local maximum seemed to be 790 nanobots in range, at <30714324,35570402,47303309>
        assertSolution("?", "input-day23-2018.txt");
    }
}
