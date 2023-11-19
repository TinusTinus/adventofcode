package nl.mvdr.adventofcode.adventofcode2021.day02;

import java.util.stream.Stream;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * A movement instruction.
 *
 * @author Martijn van de Rijdt
 */
enum Instruction {
    FORWARD("forward", Direction.RIGHT),
    DOWN("down", Direction.DOWN),
    UP("up", Direction.UP);
    
    private final String stringRepresentation;
    private final Direction direction;

    /**
     * Parses the text representation of an instruction.
     * 
     * @param text text representation
     * @return the instruction
     */
    static Instruction of(String text) {
        return Stream.of(values())
                .filter(value -> value.stringRepresentation.equals(text))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unexpected instruction: " + text));
    }
    
    /**
     * Constructor.
     * 
     * @param stringRepresentation string representation of the instruction
     * @param direction the direction to move the submarine, in part 1 of the puzzle
     */
    Instruction(String stringRepresentation, Direction direction) {
        this.stringRepresentation = stringRepresentation;
        this.direction = direction;
    }
    
    /**
     * Moves the given distance in this direction.
     * 
     * @param point starting location
     * @param distance the distance to move
     * @return end location
     */
    private Point move(Point point, int distance) {
        return direction.move(point, distance);
    }
    
    /**
     * Performs this instruction.
     * 
     * @param startingState starting state of the submarine
     * @param x x parameter of the command
     * @param applyAim whether the rules from part 2 of the puzzle should be applied
     * @return updated submarine state
     */
    Submarine execute(Submarine startingState, int x, boolean applyAim) {
        Submarine result;
        if (applyAim) {
            throw new IllegalStateException("Not yet implemented"); // TODO
        } else {
            var newLocation = move(startingState.location(), x);
            result = new Submarine(newLocation, 0);
        }
        return result;
    }
}
