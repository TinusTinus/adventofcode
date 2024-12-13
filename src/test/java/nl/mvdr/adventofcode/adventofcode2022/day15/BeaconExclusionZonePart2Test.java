package nl.mvdr.adventofcode.adventofcode2022.day15;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit test cases for {@link BeaconExclusionZonePart2}.
 *
 * @author Martijn van de Rijdt
 */
public class BeaconExclusionZonePart2Test {
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        BeaconExclusionZonePart2 solver = new BeaconExclusionZonePart2(20);
        
        String result = solver.solve("example-day15-2022.txt");
        
        Assertions.assertEquals("56000011", result);
    }
}
