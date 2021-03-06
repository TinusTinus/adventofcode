package nl.mvdr.adventofcode.adventofcode2018.day16;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.adventofcode2018.timetraveldevice.Opcode;

/**
 * A sample captured by monitoring the CPU.
 *
 * @author Martijn van de Rijdt
 */
class Sample {
    /** Register values before the instruction was executed. */
    private final List<Integer> registersBefore;
    /** The instruction. */
    private final OpcodeNumberInstruction instruction;
    /** Register values after the instruction was executed. */
    private final List<Integer> registersAfter;
    
    /** The opcodes, like which this sample behaves, ignoring the instruction's opcode number. */
    private final Set<Opcode> matchingOpcodes;
    
    /**
     * Parses three consecutive input lines into a sample.
     * 
     * @param registersBeforeLine line containing the registers before the instruction, for example: "Before: [3, 2, 1, 1]"
     * @param instructionLine line containing the instruction, for example: 9 2 1 2
     * @param registersAfterLine line containing the registers after the instruction, for example: "After:  [3, 2, 1, 1]"
     * @return sample
     */
    static Sample parse(String registersBeforeLine, String instructionLine, String registersAfterLine) {
        List<Integer> registersBefore = parseRegisters(registersBeforeLine);
        OpcodeNumberInstruction instruction = OpcodeNumberInstruction.parse(instructionLine);
        List<Integer> registersAfter = parseRegisters(registersAfterLine);
        
        return new Sample(registersBefore, instruction, registersAfter);
    }
    
    /**
     * Parses a line containing register values.
     * 
     * Use {@link #parse(String, String, String)} to obtain an instance of this class.
     * 
     * @param line line containing registers, for example: "Before: [3, 2, 1, 1]" or "After:  [3, 2, 1, 1]"
     * @return register values
     */
    private static List<Integer> parseRegisters(String line) {
        // Strip off the prefix "Before:  [" or "After:  [" and the suffix "]"
        String registerString = line.substring(9, line.length() - 1);
        
        String[] parts = registerString.split(", ");
        
        return Stream.of(parts)
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }
    
    /**
     * Constructor.
     * 
     * @param registersBefore register values before the instructin was executed
     * @param instruction the instruction
     * @param registersAfter register values after the instruction was executed
     */
    private Sample(List<Integer> registersBefore, OpcodeNumberInstruction instruction, List<Integer> registersAfter) {
        super();
        
        this.registersBefore = registersBefore;
        this.instruction = instruction;
        this.registersAfter = registersAfter;
        
        this.matchingOpcodes = Stream.of(Opcode.values())
                .filter(this::matches)
                .collect(Collectors.toSet());
    }
    
    /** @return whether this sample behaves like three or more opcodes */
    boolean behavesLikeAtLeastThreeOpcodes() {
        return 3 <= getMatchingOpcodes().size();
    }
    
    /** @return the opcodes, like which this sample behaves, ignoring the instruction's opcode number */
    Set<Opcode> getMatchingOpcodes() {
        return matchingOpcodes;
    }
    
    /**
     * Determines whether this sample behaves like the given opcode, ignoring the instruction's opcode number.
     * 
     * @param opcode opcode
     * @return whether this sample behaves like the opcode
     */
    private boolean matches(Opcode opcode) {
        List<Integer> opcodeResult = opcode.perform(instruction.getA(), instruction.getB(), instruction.getC(), registersBefore);
        return registersAfter.equals(opcodeResult);
    }
    
    OpcodeNumberInstruction getInstruction() {
        return instruction;
    }
    
    @Override
    public String toString() {
        return registersBefore + " " + instruction + " " + registersAfter;
    }
}
