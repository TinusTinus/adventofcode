package nl.mvdr.adventofcode.adventofcode2018.day16;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link OpcodeNumberInstruction}.
 *
 * @author Martijn van de Rijdt
 */
public class InstructionTest {
    /** Test case for {@link OpcodeNumberInstruction#parse(String)}. */
    @Test
    public void testParse() {
        String input = "9 2 1 2";
        
        OpcodeNumberInstruction instruction = OpcodeNumberInstruction.parse(input);
        
        Assertions.assertEquals(9, instruction.getOpcodeNumber());
        Assertions.assertEquals(2, instruction.getA());
        Assertions.assertEquals(1, instruction.getB());
        Assertions.assertEquals(2, instruction.getC());
    }
    
    /** Test case for {@link OpcodeNumberInstruction#parse(String)} and {@link OpcodeNumberInstruction#toString()}. */
    @Test
    public void testToString() {
        String input = "9 2 1 2";
        
        OpcodeNumberInstruction instruction = OpcodeNumberInstruction.parse(input);
        String string = instruction.toString();
        
        Assertions.assertEquals(input, string);
    }
}
