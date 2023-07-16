package nl.mvdr.adventofcode.adventofcode2022.day15;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link BeaconExclusionZonePart1}.
 *
 * @author Martijn van de Rijdt
 */
public class BeaconExclusionZonePart1Test extends SolverTest<BeaconExclusionZonePart1> {

    /** Constructor. */
    public BeaconExclusionZonePart1Test() {
        super(BeaconExclusionZonePart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution(new BeaconExclusionZonePart1(10), "26", "example-day15-2022.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("?", "input-day15-2022.txt");
    }
}
