package nl.mvdr.adventofcode.adventofcode2018.day19;

import nl.mvdr.adventofcode.adventofcode2018.opcode.Opcode;

/**
 * Representation of an instruction.
 *
 * @author Martijn van de Rijdt
 */
class Instruction {
    
    /** The opcode. */
    private final Opcode opcode;
    /** Input A. Either an immediate value or a register number. */
    private final int a;
    /** Input B. Either an immediate value or a register number. */
    private final int b;
    /** Number of the register to which to write the output. */
    private final int c;
    

    /**
     * Parses the given line into an instruction.
     * 
     * @param line textual representation of the instruction, for example: addr 2 1 2
     * @return instruction
     */
    static Instruction parse(String line) {
        String[] parts = line.split(" ");
        
        if (parts.length != 4) {
            throw new IllegalArgumentException("Unable to parse input, unexpected number of parts: " + line);
        }
        
        Opcode opcode = Opcode.parse(parts[0]);
        int a = Integer.parseInt(parts[1]);
        int b = Integer.parseInt(parts[2]);
        int c = Integer.parseInt(parts[3]);
        
        return new Instruction(opcode, a, b, c);
    }
    
    /** @return the opcode */
    Opcode getOpcode() {
        return opcode;
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
     * Constructor.
     * 
     * Use {@link #parse(String)} to obtain an instance of this class.
     * 
     * @param opcode the opcode
     * @param a input A; either an immediate value or a register number
     * @param b input B; either an immediate value or a register number
     * @param c number of the register to which to write the output
     */
    private Instruction(Opcode opcode, int a, int b, int c) {
        this.opcode = opcode;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Executes this instruction.
     * 
     * Note: this method does not modify the register array; instead it returns a new array containing the output values.
     * 
     * @param registers register values before the operation
     * @return a new array, containing the register values after the operation
     */
    int[] execute(int[] registers) {
        return opcode.perform(a, b, c, registers);
    }
    
    @Override
    public String toString() {
        return opcode + " " + a + " " + b + " " + c;
    }
}
