package nl.mvdr.adventofcode.adventofcode2018.timetraveldevice;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test cases for {@link Opcode}.
 *
 * @author Martijn van de Rijdt
 */
public class OpcodeTest {
    /**
     * Test case for {@link Opcode#MULR} based on an example from the puzzle:
     * 
     * Before: [3, 2, 1, 1]
     * 9 2 1 2
     * After: [3, 2, 2, 1]
     * 
     * Opcode 9 could be mulr: register 2 (which has a value of 1) times register 1
     * (which has a value of 2) produces 2, which matches the value stored in the
     * output register, register 2.
     */
    @SuppressWarnings("boxing")
    @Test
    public void testExampleMulr() {
        int a = 2;
        int b = 1;
        int c = 2;
        List<Integer> registers = List.of(3, 2, 1, 1);
        
        List<Integer> result = Opcode.MULR.perform(a, b, c, registers);
        
        Assertions.assertEquals(List.of(3, 2, 2, 1), result);
    }
    
    /**
     * Test case for {@link Opcode#ADDI} based on an example from the puzzle:
     * 
     * Before: [3, 2, 1, 1]
     * 9 2 1 2
     * After: [3, 2, 2, 1]
     * 
     * Opcode 9 could be addi: register 2 (which has a value of 1) plus value 1
     * produces 2, which matches the value stored in the output register, register
     * 2.
     */
    @SuppressWarnings("boxing")
    @Test
    public void testExampleAddi() {
        int a = 2;
        int b = 1;
        int c = 2;
        List<Integer> registers = List.of(3, 2, 1, 1);
        
        List<Integer> result = Opcode.ADDI.perform(a, b, c, registers);
        
        Assertions.assertEquals(List.of(3, 2, 2, 1), result);
    }
    
    /**
     * Test case for {@link Opcode#SETI} based on an example from the puzzle:
     * 
     * Before: [3, 2, 1, 1]
     * 9 2 1 2
     * After: [3, 2, 2, 1]
     * 
     * Opcode 9 could be seti: value 2 matches the value stored in the output
     * register, register 2; the number given for B is irrelevant.
     */
    @SuppressWarnings("boxing")
    @Test
    public void testExampleSeti() {
        int a = 2;
        int b = 1;
        int c = 2;
        List<Integer> registers = List.of(3, 2, 1, 1);
        
        List<Integer> result = Opcode.SETI.perform(a, b, c, registers);
        
        Assertions.assertEquals(List.of(3, 2, 2, 1), result);
    }
    
    /**
     * Test case for {@link Opcode#SETI} based on an example from the puzzle:
     * 
     * Before: [3, 2, 1, 1]
     * 9 2 1 2
     * After: [3, 2, 2, 1]
     * 
     * Opcode 9 could be any of the 16 opcodes listed above, but only three of them
     * behave in a way that would cause the result shown in the sample: mulr, addi
     * and seti.
     */
    @SuppressWarnings("boxing")
    @Test
    public void testExample() {
        int a = 2;
        int b = 1;
        int c = 2;
        List<Integer> registers = List.of(3, 2, 1, 1);
        
        for (Opcode opcode: Opcode.values()) {
            if (opcode != Opcode.MULR && opcode != Opcode.ADDI && opcode != Opcode.SETI) {
                List<Integer> result = opcode.perform(a, b, c, registers);
                Assertions.assertNotEquals(List.of(3, 2, 2, 1), result);
            }
        }
    }
    
    /** Test case for {@link Opcode#parse(String)}. */
    @Test
    public void testParse() {
        String input = "addr";
        
        Opcode result = Opcode.parse(input);
        
        Assertions.assertEquals(Opcode.ADDR, result);
    }
    
    /** Test case for {@link Opcode#parse(String)}. */
    @Test
    public void testParseInvalidInput() {
        String input = "herpderp";
        
        Assertions.assertThrows(NoSuchElementException.class, () -> Opcode.parse(input));
    }
}
