package nl.mvdr.adventofcode.adventofcode2018.timetraveldevice;

import java.util.List;

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
    
    /** Test case for {@link Instruction#execute(List)}. */
    @Test
    public void testExecute() {
        Instruction instruction = Instruction.parse("mulr 2 1 2");
        List<Integer> registers = List.of(3, 2, 1, 1);
        
        List<Integer> result = instruction.execute(registers);
        
        Assertions.assertEquals(List.of(3, 2, 2, 1), result);
    }
}
