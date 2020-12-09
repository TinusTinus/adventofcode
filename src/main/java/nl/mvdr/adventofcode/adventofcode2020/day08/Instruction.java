package nl.mvdr.adventofcode.adventofcode2020.day08;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * An instruction in handheld console boot code.
 *
 * @author Martijn van de Rijdt
 */
record Instruction(Operation operation, int argument) {

    /**
     * Parses a list of instructions.
     * 
     * @param lines puzzle input
     * @return list of instructions
     */
    static List<Instruction> parse(Stream<String> lines) {
        return lines.filter(Predicate.not(String::isEmpty))
                .map(Instruction::parseInstruction)
                .collect(Collectors.toList());
    }
    
    /**
     * Parses an instruction.
     * 
     * @param text textual representation of an instruction, for example: "acc +2"
     */
    private static Instruction parseInstruction(String text) {
        String[] parts = text.split(" ");
        Operation operation = Operation.fromString(parts[0]);
        int argument = Integer.parseInt(parts[1]);
        return new Instruction(operation, argument);
    }
    
    /**
     * Executes this instruction.
     * 
     * @param inputState program state before executing the operation
     * @return program state after executing the instruction
     */
    ProgramState execute(ProgramState inputState) {
        return operation.execute(inputState, argument);
    }
}
