package nl.mvdr.adventofcode.adventofcode2019.day08;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.adventofcode2019.day07.AmplificationCircuitPart1;

/**
 * Unit test cases for {@link AmplificationCircuitPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SpaceImageFormatPart1Test {
    
    /** Test case based on the example from the puzzle text. */
    @Test
    public void testExample() {
        SpaceImageFormatPart1 solver = new SpaceImageFormatPart1(3, 2);
        
        String result = solver.solve("example-day08-2019-0.txt");

        Assertions.assertEquals("1", result);
    }
}
