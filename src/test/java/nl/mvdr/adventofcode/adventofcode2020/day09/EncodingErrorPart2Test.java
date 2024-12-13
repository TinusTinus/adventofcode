package nl.mvdr.adventofcode.adventofcode2020.day09;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit test cases for {@link EncodingErrorPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class EncodingErrorPart2Test {

    /**
     * Test case based on an example from the puzzle text.
     * 
     * In this example, after the 5-number preamble, almost every number is the sum
     * of two of the previous 5 numbers; the only number that does not follow this
     * rule is 127.
     */
    @Test
    public void testExample() {
        EncodingErrorPart2 solver = new EncodingErrorPart2(5);
        
        String result = solver.solve("example-day09-2020.txt");
        
        Assertions.assertEquals("62", result);
    }
}
