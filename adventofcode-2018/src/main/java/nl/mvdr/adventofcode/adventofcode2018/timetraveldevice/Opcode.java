package nl.mvdr.adventofcode.adventofcode2018.timetraveldevice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Definition of an opcode, for the programmable time travel device featured in multiple puzzles.
 *
 * @author Martijn van de Rijdt
 */
public enum Opcode {
    // Addition
    /** Opcode addr (add register) stores into register C the result of adding register A and register B. */
    ADDR((a, b, registers) -> registers.get(a).intValue() + registers.get(b).intValue()),
    /** Opcode addi (add immediate) stores into register C the result of adding register A and value B. */
    ADDI((a, b, registers) -> registers.get(a).intValue() + b),
    // Multiplication
    /** Opcode mulr (multiply register) stores into register C the result of multiplying register A and register B. */
    MULR((a, b, registers) -> registers.get(a).intValue() * registers.get(b).intValue()),
    /** Opcode muli (multiply immediate) stores into register C the result of multiplying register A and value B. */
    MULI((a, b, registers) -> registers.get(a).intValue() * b),
    // Bitwise AND
    /** Opcode banr (bitwise AND register) stores into register C the result of the bitwise AND of register A and register B. */
    BANR((a, b, registers) -> registers.get(a).intValue() & registers.get(b).intValue()),
    /** Opcode bani (bitwise AND immediate) stores into register C the result of the bitwise AND of register A and value B. */
    BANI((a, b, registers) -> registers.get(a).intValue() & b),
    // Bitwise OR
    /** Opcode borr (bitwise OR register) stores into register C the result of the bitwise OR of register A and register B. */
    BORR((a, b, registers) -> registers.get(a).intValue() | registers.get(b).intValue()),
    /** Opcode bori (bitwise OR immediate) stores into register C the result of the bitwise OR of register A and value B. */
    BORI((a, b, registers) -> registers.get(a).intValue() | b),
    // Assignment
    /** Opcode setr (set register) copies the contents of register A into register C. (Input B is ignored.) */
    SETR((a, b, registers) -> registers.get(a).intValue()),
    /** Opcode seti (set immediate) stores value A into register C. (Input B is ignored.) */
    SETI((a, b, registers) -> a),
    // Greater-than testing
    /** Opcode gtir (greater-than immediate/register) sets register C to 1 if value A is greater than register B. Otherwise, register C is set to 0. */
    GTIR((a, b, registers) -> a > registers.get(b).intValue() ? 1 : 0),
    /** Opcode gtri (greater-than register/immediate) sets register C to 1 if register A is greater than value B. Otherwise, register C is set to 0. */
    GTRI((a, b, registers) -> registers.get(a).intValue() > b ? 1 : 0),
    /** Opcode gtrr (greater-than register/register) sets register C to 1 if register A is greater than register B. Otherwise, register C is set to 0. */
    GTRR((a, b, registers) -> registers.get(a).intValue() > registers.get(b).intValue() ? 1 : 0),
    //Equality testing
    /** Opcode eqir (equal immediate/register) sets register C to 1 if value A is equal to register B. Otherwise, register C is set to 0. */
    EQIR((a, b, registers) -> a == registers.get(b).intValue() ? 1 : 0),
    /** Opcode eqri (equal register/immediate) sets register C to 1 if register A is equal to value B. Otherwise, register C is set to 0. */
    EQRI((a, b, registers) -> registers.get(a).intValue() == b ? 1 : 0),
    /** Opcode eqrr (equal register/register) sets register C to 1 if register A is equal to register B. Otherwise, register C is set to 0. */
    EQRR((a, b, registers) -> registers.get(a).intValue() == registers.get(b).intValue() ? 1 : 0);
    
    /** Logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(Opcode.class);
    
    /** Function to compute the output for this opcode. */
    private final Operation operation;
    
    /**
     * Constructor.
     * 
     * @param operation function to compute the output for this opcode
     */
    Opcode(Operation operation) {
        this.operation = operation;
    }
    
    /**
     * Performs the operation for this opcode.
     * 
     * Note: this method does not modify the register array; instead it returns a new array containing the output values.
     * 
     * @param a input A; either an immediate value or a register number
     * @param b input B; either an immediate value or a register number
     * @param c number of the register to which to write the output
     * @param registers register values before the operation
     * @return a new list, containing the register values after the operation
     */
    public List<Integer> perform(int a, int b, int c, List<Integer> registers) {
        int outputValue = operation.computeOutput(a, b, registers);
        
        List<Integer> result = new ArrayList<>(registers);
        result.set(c, Integer.valueOf(outputValue));
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("{} {} {} {} {} -> {}", registers, this, Integer.valueOf(a), Integer.valueOf(b), Integer.valueOf(c), result);
        }
        
        return result;
    }
    
    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
    
    /**
     * Parses the given string into an Opcode.
     * 
     * @param text text; should contain the lowercase name of an opcode (for example: "addr").
     * @return corresponding opcode
     */
    public static Opcode parse(String text) {
        return Stream.of(Opcode.values())
                .filter(opcode -> opcode.toString().equals(text))
                .findFirst()
                .get();
    }
}
