package nl.mvdr.adventofcode.adventofcode2018.day16;

import java.util.stream.Stream;

/**
 * A sample captured by monitoring the CPU.
 *
 * @author Martijn van de Rijdt
 */
class Sample {
    /** Register values before the instruction was executed. */
    private final int[] registersBefore;
    /** The instruction. */
    private final Instruction instruction;
    /** Register values after the instruction was executed. */
    private final int[] registersAfter;
    
    /**
     * Parses three consecutive input lines into a sample.
     * 
     * @param registersBeforeLine line containing the registers before the instruction, for example: "Before: [3, 2, 1, 1]"
     * @param instructionLine line containing the instruction, for example: 9 2 1 2
     * @param registersAfterLine line containing the registers after the instruction, for example: "After:  [3, 2, 1, 1]"
     * @return sample
     */
    static Sample parse(String registersBeforeLine, String instructionLine, String registersAfterLine) {
        int[] registersBefore = parseRegisters(registersBeforeLine);
        Instruction instruction = Instruction.parse(instructionLine);
        int[] registersAfter = parseRegisters(registersAfterLine);
        
        return new Sample(registersBefore, instruction, registersAfter);
    }
    
    /**
     * Parses a line containing register values.
     * 
     * @param line line containing registers, for example: "Before: [3, 2, 1, 1]" or "After:  [3, 2, 1, 1]"
     * @return register values
     */
    private static int[] parseRegisters(String line) {
        // Strip off the prefix "Before:  [" or "After:  [" and the suffix "]"
        String registerString = line.substring(9, 1);
        
        String[] parts = registerString.split(",");
        
        return Stream.of(parts)
                .mapToInt(Integer::parseInt)
                .toArray();
    }
    
    /**
     * Constructor.
     * 
     * @param registersBefore register values before the instructin was executed
     * @param instruction the instruction
     * @param registersAfter register values after the instruction was executed
     */
    private Sample(int[] registersBefore, Instruction instruction, int[] registersAfter) {
        super();
        this.registersBefore = registersBefore;
        this.instruction = instruction;
        this.registersAfter = registersAfter;
    }
}
