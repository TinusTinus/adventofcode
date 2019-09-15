package nl.mvdr.adventofcode.adventofcode2017.day18;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
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
        
        Instruction result;
        if (SoundInstruction.NAME.equals(parts[0])) {
            result = new SoundInstruction(parts[1]);
        } else if (SetInstruction.NAME.equals(parts[0])) {
            result = new SetInstruction(parts[1], parts[2]);
        } else if (AddInstruction.NAME.equals(parts[0])) {
            result = new AddInstruction(parts[1], parts[2]);
        } else if (MultiplyInstruction.NAME.equals(parts[0])) {
            result = new MultiplyInstruction(parts[1], parts[2]);
        } else if (ModuloInstruction.NAME.equals(parts[0])) {
            result = new ModuloInstruction(parts[1], parts[2]);
        } else if (RecoverInstruction.NAME.equals(parts[0])) {
            result = new RecoverInstruction(parts[1]);
        } else if (JumpInstruction.NAME.equals(parts[0])) {
            result = new JumpInstruction(parts[1], parts[2]);
        } else {
            throw new IllegalArgumentException("Unknown parts[0]: " + parts[0]);
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
