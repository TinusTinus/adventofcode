package nl.mvdr.adventofcode.adventofcode2018.day23;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link ExperimentalEmergencyTeleportationPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ExperimentalEmergencyTeleportationPart1Test extends SolverTest<ExperimentalEmergencyTeleportationPart1> {
    /** Constructor. */
    public ExperimentalEmergencyTeleportationPart1Test() {
        super(ExperimentalEmergencyTeleportationPart1.class);
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample() {
        assertSolution("7", "example-day23-2018-0.txt");
    }
    
    /** Test case based on the accepted solution to the puzzle. */
    @Test
    public void testSolution() {
        assertSolution("248", "input-day23-2018.txt");
    }
}
