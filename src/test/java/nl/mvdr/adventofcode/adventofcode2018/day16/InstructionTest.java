package nl.mvdr.adventofcode.adventofcode2018.day16;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link Instruction}.
 *
 * @author Martijn van de Rijdt
 */
public class InstructionTest {
    /** Test case for {@link Instruction#parse(String)}. */
    @Test
    public void testParse() {
        String input = "9 2 1 2";
        
        Instruction instruction = Instruction.parse(input);
        
        Assertions.assertEquals(9, instruction.getOpcodeNumber());
        Assertions.assertEquals(2, instruction.getA());
        Assertions.assertEquals(1, instruction.getB());
        Assertions.assertEquals(2, instruction.getC());
    }
    
    /** Test case for {@link Instruction#parse(String)} and {@link Instruction#toString()}. */
    @Test
    public void testToString() {
        String input = "9 2 1 2";
        
        Instruction instruction = Instruction.parse(input);
        String string = instruction.toString();
        
        Assertions.assertEquals(input, string);
    }
}
