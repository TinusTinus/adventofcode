package nl.mvdr.adventofcode.adventofcode2020.day09;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit test cases for {@link EncodingErrorPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class EncodingErrorPart1Test {

    /**
     * Test case based on an example from the puzzle text.
     * 
     * In this example, after the 5-number preamble, almost every number is the sum
     * of two of the previous 5 numbers; the only number that does not follow this
     * rule is 127.
     */
    @Test
    public void testExample() {
        EncodingErrorPart1 solver = new EncodingErrorPart1(5);
        
        String result = solver.solve("example-day09-2020.txt");
        
        Assertions.assertEquals("127", result);
    }
}
