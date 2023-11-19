package nl.mvdr.adventofcode.adventofcode2021.day02;

/**
 * A movement instruction.
 *
 * @author Martijn van de Rijdt
 */
enum Instruction {
    FORWARD("forward"),
    DOWN("down"),
    UP("up");
    
    private final String stringRepresentation;

    /**
     * Constructor.
     * 
     * @param stringRepresentation string representation of the instruction
     */
    Instruction(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }
}
