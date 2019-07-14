package nl.mvdr.adventofcode.adventofcode2018.opcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.adventofcode2018.opcode.Instruction;
import nl.mvdr.adventofcode.adventofcode2018.opcode.Opcode;

/**
 * Test class for {@link Instruction}.
 *
 * @author Martijn van de Rijdt
 */
public class InstructionTest {
    /** Test case for {@link Instruction#parse(String)}. */
    @Test
    public void testParse() {
        String input = "addr 2 1 2";
        
        Instruction instruction = Instruction.parse(input);
        
        Assertions.assertEquals(Opcode.ADDR, instruction.getOpcode());
        Assertions.assertEquals(2, instruction.getA());
        Assertions.assertEquals(1, instruction.getB());
        Assertions.assertEquals(2, instruction.getC());
    }
    
    /** Test case for {@link Instruction#parse(String)} and {@link Instruction#toString()}. */
    @Test
    public void testToString() {
        String input = "addr 2 1 2";
        
        Instruction instruction = Instruction.parse(input);
        String string = instruction.toString();
        
        Assertions.assertEquals(input, string);
    }
    
    /** Test case for {@link Instruction#execute(int[])}. */
    @Test
    public void testExecute() {
        Instruction instruction = Instruction.parse("mulr 2 1 2");
        int[] registers = { 3, 2, 1, 1 };
        
        int[] result = instruction.execute(registers);
        
        Assertions.assertArrayEquals(new int[] { 3, 2, 2, 1 }, result);
    }
}
