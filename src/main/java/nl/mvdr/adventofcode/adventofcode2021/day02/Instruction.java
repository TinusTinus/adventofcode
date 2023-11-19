package nl.mvdr.adventofcode.adventofcode2021.day02;

import java.util.stream.Stream;

import nl.mvdr.adventofcode.point.Direction;

/**
 * A movement instruction.
 *
 * @author Martijn van de Rijdt
 */
enum Instruction {
    FORWARD("forward", Direction.RIGHT) {
        @Override
        Submarine applyAim(Submarine startingState, int x) {
            var location = startingState.location();
            // increase horizontal position by x units
            location = Direction.RIGHT.move(location, x);
            // increase depth by aim multiplied by x
            location = Direction.DOWN.move(location, startingState.aim() * x);
            return new Submarine(location, startingState.aim());
        }
    },
    DOWN("down", Direction.DOWN) {
        @Override
        Submarine applyAim(Submarine startingState, int x) {
            // increase the aim
            return new Submarine(startingState.location(), startingState.aim() + x);
        }
    },
    UP("up", Direction.UP) {
        @Override
        Submarine applyAim(Submarine startingState, int x) {
            // decrease the aim
            return new Submarine(startingState.location(), startingState.aim() - x);
        }
    };
    
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
            result = applyAim(startingState, x);
        } else {
            result = move(startingState, x);
        }
        return result;
    }

    /**
     * Moves the given distance in this direction.
     * 
     * @param point starting location
     * @param distance the distance to move
     * @return end state
     */
    private Submarine move(Submarine startingState, int distance) {
        var newLocation = direction.move(startingState.location(), distance);
        return new Submarine(newLocation, startingState.aim());
    }
    
    /**
     * Applies the rules as specified in part 2 of the puzzle by applying the "aim" parameter.
     * 
     * @param startingState starting state of the submarine
     * @param x x parameter of the command
     * @return updated submarine state
     */
    abstract Submarine applyAim(Submarine startingState, int x);
}
