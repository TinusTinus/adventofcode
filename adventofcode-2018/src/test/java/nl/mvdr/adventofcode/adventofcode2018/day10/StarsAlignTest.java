package nl.mvdr.adventofcode.adventofcode2018.day10;

import org.junit.jupiter.api.Test;

/**
 * Unit test for {@link StarsAlign}.
 *
 * @author Martijn van de Rijdt
 */
public class StarsAlignTest {
    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample() {
        StarsAlign solver = new StarsAlign();
        
        solver.solve("example-day10-2018.txt");
    }
}
