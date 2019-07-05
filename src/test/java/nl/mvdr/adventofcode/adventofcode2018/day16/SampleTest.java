package nl.mvdr.adventofcode.adventofcode2018.day16;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test cases for {@link Sample}.
 *
 * @author Martijn van de Rijdt
 */
public class SampleTest {
    /** Test case for {@link Sample#getMatchingOpcodes()}, based on the example from the puzzle. */
    @Test
    public void testGetMatchingOpcodes() {
        String beforeLine = "Before: [3, 2, 1, 1]";
        String instructionLine = "9 2 1 2";
        String afterLine = "After:  [3, 2, 2, 1]";

        Sample sample = Sample.parse(beforeLine, instructionLine, afterLine);
        Set<Opcode> matchingOpcodes = sample.getMatchingOpcodes();
        
        Assertions.assertEquals(Set.of(Opcode.MULR, Opcode.ADDI, Opcode.SETI), matchingOpcodes);
    }
    
    /** Test case for {@link Sample#behavesLikeAtLeastThreeOpcodes()}, based on the example from the puzzle. */
    @Test
    public void testAtLeastThreeOpcodes() {
        String beforeLine = "Before: [3, 2, 1, 1]";
        String instructionLine = "9 2 1 2";
        String afterLine = "After:  [3, 2, 2, 1]";

        Sample sample = Sample.parse(beforeLine, instructionLine, afterLine);
        boolean result = sample.behavesLikeAtLeastThreeOpcodes();
        
        Assertions.assertTrue(result);
    }
}
