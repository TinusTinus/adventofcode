package nl.mvdr.adventofcode.adventofcode2022.day15;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link BeaconExclusionZonePart2}.
 *
 * @author Martijn van de Rijdt
 */
public class BeaconExclusionZonePart2Test extends SolverTest<BeaconExclusionZonePart2> {

    /** Constructor. */
    public BeaconExclusionZonePart2Test() {
        super(BeaconExclusionZonePart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution(new BeaconExclusionZonePart2(20), "56000011", "example-day15-2022.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    @Disabled // long-running test case
    public void testSolution() {
        assertSolution("12691026767556", "input-day15-2022.txt");
    }
}
