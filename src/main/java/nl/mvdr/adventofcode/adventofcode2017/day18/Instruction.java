package nl.mvdr.adventofcode.adventofcode2017.day18;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.OptionalInt;
import java.util.stream.Collectors;

/**
 * An instruction.
 *
 * @author Martijn van de Rijdt
 */
interface Instruction {
    
    /**
     * Parses the instructions in the input file.
     * 
     * @param inputFilePath path to the text file containing textual representations of instructions
     * @return instructions
     * @throws IOException if the input could not be read
     */
    static List<Instruction> parseInstructions(Path inputFilePath) throws IOException {
        return Files.lines(inputFilePath)
                // ignore empty lines (the last line in the file)
                .filter(Objects::nonNull)
                .filter(line -> !line.isBlank())
                // parse each line
                .map(Instruction::parseInstruction)
                .collect(Collectors.toList());
    }
    
    /**
     * Parses a single instruction.
     * 
     * @param text text representation of an instruction
     * @return instruction
     */
    private static Instruction parseInstruction(String text) {
        String[] parts = text.split(" ");
        
        String keyword = parts[0];
        
        String register = parts[1];
        
        OptionalInt value;
        if (1 < parts.length) {
            value = OptionalInt.of(Integer.parseInt(parts[2]));
        } else {
            value = OptionalInt.empty();
        }
        
        return createInstruction(keyword, register, value);
    }

    private static Instruction createInstruction(String keyword, String register, OptionalInt value) {
        Instruction result;
        if (SoundInstruction.NAME.equals(keyword)) {
            result = new SoundInstruction(register);
        } else if (SetInstruction.NAME.equals(keyword)) {
            result = new SetInstruction(register, value.getAsInt());
        } else if (AddInstruction.NAME.equals(keyword)) {
            result = new AddInstruction(register, value.getAsInt());
        } else if (MultiplyInstruction.NAME.equals(keyword)) {
            result = new MultiplyInstruction(register, value.getAsInt());
        } else if (ModuloInstruction.NAME.equals(keyword)) {
            result = new ModuloInstruction(register, value.getAsInt());
        } else if (RecoverInstruction.NAME.equals(keyword)) {
            result = new RecoverInstruction(register);
        } else if (JumpInstruction.NAME.equals(keyword)) {
            result = new JumpInstruction(register, value.getAsInt());
        } else {
            throw new IllegalArgumentException("Unknown keyword: " + keyword);
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
}
