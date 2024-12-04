package nl.mvdr.adventofcode.adventofcode2019.day08;

import org.junit.jupiter.api.Test;

/**
 * Unit test cases for {@link SpaceImageFormatPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class SpaceImageFormatPart2Test {

    /**
     * Test case based on the example from the puzzle text.
     * 
     * Inspect the log for the actual decoded image; this should be:
     * <pre>
     *  #
     * # 
     * </pre>
     */
    @Test
    public void testExample() {
        SpaceImageFormatPart2 solver = new SpaceImageFormatPart2(2, 2);
        
        solver.solve("example-day08-2019-1.txt");
    }
}
