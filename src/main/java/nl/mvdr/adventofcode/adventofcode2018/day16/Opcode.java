package nl.mvdr.adventofcode.adventofcode2018.day16;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Definition of an opcode.
 *
 * @author Martijn van de Rijdt
 */
enum Opcode {
    // Addition
    /** Opcode addr (add register) stores into register C the result of adding register A and register B. */
    ADDR((a, b, registers) -> registers[a] + registers[b]),
    /** Opcode addi (add immediate) stores into register C the result of adding register A and value B. */
    ADDI((a, b, registers) -> registers[a] + b),
    // Multiplication
    /** Opcode mulr (multiply register) stores into register C the result of multiplying register A and register B. */
    MULR((a, b, registers) -> registers[a] * registers[b]),
    /** Opcode muli (multiply immediate) stores into register C the result of multiplying register A and value B. */
    MULI((a, b, registers) -> registers[a] * b),
    // Bitwise AND
    /** Opcode banr (bitwise AND register) stores into register C the result of the bitwise AND of register A and register B. */
    BANR((a, b, registers) -> registers[a] & registers[b]),
    /** Opcode bani (bitwise AND immediate) stores into register C the result of the bitwise AND of register A and value B. */
    BANI((a, b, registers) -> registers[a] & b),
    // Bitwise OR
    /** Opcode borr (bitwise OR register) stores into register C the result of the bitwise OR of register A and register B. */
    BORR((a, b, registers) -> registers[a] | registers[b]),
    /** Opcode bori (bitwise OR immediate) stores into register C the result of the bitwise OR of register A and value B. */
    BORI((a, b, registers) -> registers[a] | b),
    // Assignment
    /** Opcode setr (set register) copies the contents of register A into register C. (Input B is ignored.) */
    SETR((a, b, registers) -> registers[a]),
    /** Opcode seti (set immediate) stores value A into register C. (Input B is ignored.) */
    SETI((a, b, registers) -> a),
    // Greater-than testing
    /** Opcode gtir (greater-than immediate/register) sets register C to 1 if value A is greater than register B. Otherwise, register C is set to 0. */
    GTIR((a, b, registers) -> a > registers[b] ? 1 : 0),
    /** Opcode gtri (greater-than register/immediate) sets register C to 1 if register A is greater than value B. Otherwise, register C is set to 0. */
    GTRI((a, b, registers) -> registers[a] > b ? 1 : 0),
    /** Opcode gtrr (greater-than register/register) sets register C to 1 if register A is greater than register B. Otherwise, register C is set to 0. */
    GTRR((a, b, registers) -> registers[a] > registers[b] ? 1 : 0),
    //Equality testing
    /** Opcode eqir (equal immediate/register) sets register C to 1 if value A is equal to register B. Otherwise, register C is set to 0. */
    EQIR((a, b, registers) -> a == registers[b] ? 1 : 0),
    /** Opcode eqri (equal register/immediate) sets register C to 1 if register A is equal to value B. Otherwise, register C is set to 0. */
    EQRI((a, b, registers) -> registers[a] == b ? 1 : 0),
    /** Opcode eqrr (equal register/register) sets register C to 1 if register A is equal to register B. Otherwise, register C is set to 0. */
    EQRR((a, b, registers) -> registers[a] == registers[b] ? 1 : 0);
    
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
     * @param a input; either an immediate value or a register number
     * @param b input; either an immediate value or a register number
     * @param c number of the register to which to write the output
     * @param registers register values before the operation
     * @return a new array, containing the register values after the operation
     */
    int[] perform(int a, int b, int c, int[] registers) {
        int outputValue = operation.computeOutput(a, b, registers);
        
        int[] result = Arrays.copyOf(registers, registers.length);
        result[c] = outputValue;
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("{} {} {} {} {} -> {}", Arrays.toString(registers), this, a, b, c, Arrays.toString(result));
        }
        
        return result;
    }
}
