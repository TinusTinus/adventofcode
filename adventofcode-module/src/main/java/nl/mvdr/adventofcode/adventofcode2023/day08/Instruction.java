package nl.mvdr.adventofcode.adventofcode2023.day08;

import java.util.List;
import java.util.stream.Stream;

/**
 * A left/right instruction.
 *
 * @author Martijn van de Rijdt
 */
enum Instruction {
    /** Take the left path. */
    LEFT('L'),
    /** Take the right path. */
    RIGHT('R');
    
    private final char representation;
    
    /**
     * Constructor.
     * 
     * @param representation single-character representation of this instruction
     */
    Instruction(char representation) {
        this.representation = representation;
    }

    /**
     * Parses a textual representation of a list of left/right instructions.
     * 
     * @param text instructions, for example: "LLR"
     * @return list of instructions
     */
    static List<Instruction> parse(String text) {
        return text.chars()
                .mapToObj(c -> parse((char) c))
                .toList();
    }
    
    /**
     * Parses a textual representation of a left/right instruction.
     * 
     * @param c single-character representation of an instruction: 'L' or 'R'
     * @return instruction
     */
    private static Instruction parse(char c) {
        return Stream.of(values())
                .filter(value -> value.representation == c)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unable to parse as instruction: " + c));
    }
}
