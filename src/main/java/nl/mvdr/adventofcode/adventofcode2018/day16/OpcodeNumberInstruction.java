package nl.mvdr.adventofcode.adventofcode2018.day16;

import java.util.function.IntFunction;

import nl.mvdr.adventofcode.adventofcode2018.timetraveldevice.Instruction;
import nl.mvdr.adventofcode.adventofcode2018.timetraveldevice.Opcode;

/**
 * Representation of an instruction, where the opcode is represented by a numeric value.
 *
 * @author Martijn van de Rijdt
 */
class OpcodeNumberInstruction {
    
    /** Number of the opcode. */
    private final int opcodeNumber;
    /** Input A. Either an immediate value or a register number. */
    private final int a;
    /** Input B. Either an immediate value or a register number. */
    private final int b;
    /** Number of the register to which to write the output. */
    private final int c;
    
    /**
     * Parses the given line into an instruction.
     * 
     * @param line textual representation of the instruction, for example: 9 2 1 2
     * @return instruction
     */
    static OpcodeNumberInstruction parse(String line) {
        String[] parts = line.split(" ");
        
        if (parts.length != 4) {
            throw new IllegalArgumentException("Unable to parse input, unexpected number of parts: " + line);
        }
        
        int opcodeNumber = Integer.parseInt(parts[0]);
        int a = Integer.parseInt(parts[1]);
        int b = Integer.parseInt(parts[2]);
        int c = Integer.parseInt(parts[3]);
        
        return new OpcodeNumberInstruction(opcodeNumber, a, b, c);
    }
    
    /**
     * Constructor.
     * 
     * Use {@link #parse(String)} to obtain an instance of this class.
     * 
     * @param opcodeNumber number of the opcode
     * @param a input A; either an immediate value or a register number
     * @param b input B; either an immediate value or a register number
     * @param c number of the register to which to write the output
     */
    private OpcodeNumberInstruction(int opcodeNumber, int a, int b, int c) {
        this.opcodeNumber = opcodeNumber;
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    /** @return number of the opcode */
    int getOpcodeNumber() {
        return opcodeNumber;
    }
    
    /** @return input A; either an immediate value or a register number */
    int getA() {
        return a;
    }
    
    /** @return input B; either an immediate value or a register number */
    int getB() {
        return b;
    }
    
    /** @return number of the register to which to write the output */
    int getC() {
        return c;
    }
    
    /**
     * Converts this instruction to an {@link Instruction}.
     * 
     * @param opcodeMapping mapping of opcode numbers to actual {@link Opcode} values
     * @return instruction
     */
    Instruction toInstruction(IntFunction<Opcode> opcodeMapping) {
        return new Instruction(opcodeMapping.apply(opcodeNumber), a, b, c);
    }
    
    @Override
    public String toString() {
        return opcodeNumber + " " + a + " " + b + " " + c;
    }
}
