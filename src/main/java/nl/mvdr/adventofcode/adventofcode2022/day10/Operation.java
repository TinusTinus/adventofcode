package nl.mvdr.adventofcode.adventofcode2022.day10;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

/**
 * An operation supported by the CPU.
 *
 * @author Martijn van de Rijdt
 */
enum Operation {
    /**
     * No operation.
     * 
     * {@code noop} takes one cycle to complete. It has no other effect.
     */
    NOOP("noop"),
    /**
     * Add to the x register.
     * 
     * {@code addx V} takes two cycles to complete. After two cycles, the X register is increased by the value V. (V can be negative.)
     */
    ADDX("addx");
    
    private String stringRepresentation;
    
    /**
     * Constructor.
     * 
     * @param stringRepresentation string representation of this operation in the puzzle input
     */
    Operation(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }
    
    /**
     * Parses the given string.
     * 
     * @param stringValue string representation of an operation
     * @return the operation
     */
    static Operation parse(String stringValue) {
        return Stream.of(Operation.values())
                .filter(value -> value.stringRepresentation.equals(stringValue))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Unable to parse value: " + stringValue));
    }
}
