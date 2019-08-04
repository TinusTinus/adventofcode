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
        // Note: there are points with 890 nanobots in range.
        // 122591143 is not the correct answer: too low!
        // That may mean that there are points elsewhere with more than 890 nanobots in range...?
        assertSolution("?", "input-day23-2018.txt");
    }
}
