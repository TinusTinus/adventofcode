package nl.mvdr.adventofcode.adventofcode2017.duet;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * An instruction in (some version of) the Duet assembly language.
 *
 * @author Martijn van de Rijdt
 */
public interface Instruction {
    
    /**
     * Parses the instructions in the input file.
     * 
     * @param lines puzzle input
     * @param sound whether the sound-based instructions (sound and recover) should be used;
     *      if false, messaging operations (send and receive) are used instead
     * @return instructions
     */
    public static List<Instruction> parseInstructions(Stream<String> lines, boolean sound) {
        return lines
                // ignore empty lines (the last line in the file)
                .filter(Objects::nonNull)
                .filter(line -> !line.isBlank())
                // parse each line
                .map(text -> parseInstruction(text, sound))
                .collect(Collectors.toList());
    }
    
    /**
     * Parses a single instruction.
     * 
     * @param text text representation of an instruction
     * @param sound whether the sound-based instructions (sound and recover) should be used;
     *      if false, messaging operations (send and receive) are used instead
     * @return instruction
     */
    private static Instruction parseInstruction(String text, boolean sound) {
        String[] parts = text.split(" ");
        
        Instruction result;
        if (sound && SoundInstruction.NAME.equals(parts[0])) {
            result = new SoundInstruction(parts[1]);
        } else if (SetInstruction.NAME.equals(parts[0])) {
            result = new SetInstruction(parts[1], parts[2]);
        } else if (AddInstruction.NAME.equals(parts[0])) {
            result = new AddInstruction(parts[1], parts[2]);
        } else if (MultiplyInstruction.NAME.equals(parts[0])) {
            result = new MultiplyInstruction(parts[1], parts[2]);
        } else if (ModuloInstruction.NAME.equals(parts[0])) {
            result = new ModuloInstruction(parts[1], parts[2]);
        } else if (sound && RecoverInstruction.NAME.equals(parts[0])) {
            result = new RecoverInstruction(parts[1]);
        } else if (JumpGreaterThanZeroInstruction.NAME.equals(parts[0])) {
            result = new JumpGreaterThanZeroInstruction(parts[1], parts[2]);
        } else if (SendInstruction.NAME.equals(parts[0])) {
            result = new SendInstruction(parts[1]);
        } else if (ReceiveInstruction.NAME.equals(parts[0])) {
            result = new ReceiveInstruction(parts[1]);
        } else if (SubtractInstruction.NAME.equals(parts[0])) {
            result = new SubtractInstruction(parts[1], parts[2]);
        } else if (JumpNotZeroInstruction.NAME.equals(parts[0])) {
            result = new JumpNotZeroInstruction(parts[1], parts[2]);
        } else {
            throw new IllegalArgumentException("Unknown instruction: " + parts[0]);
        }
        return result;
    }
    
    /**
     * Executes this instruction.
     * 
     * @param startState initial state before executing this instruction
     * @return resulting state after executing this instruction
     */
    State execute(State startState);
    
    /**
     * Whether this instruction can currently be executed.
     * 
     * @param state current program state
     * @return whether this instruction can currently be executed
     */
    default boolean canProceed(State state) {
        return true;
    }
    
    /**
     * Helper method for implementing classes, which gets the value represented by the given string.
     * 
     * @param value string representation of the value; can be a numeric value or a register name
     * @param state program state
     * @return numeric value
     */
    static long getValue(String value, State state) {
        long result;
        try {
            result = Integer.parseInt(value);
        } catch (@SuppressWarnings("unused") NumberFormatException e) {
            result = state.getRegisterValue(value);
        }
        return result;
    }
}
