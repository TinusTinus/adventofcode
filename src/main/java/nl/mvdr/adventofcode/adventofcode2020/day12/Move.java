package nl.mvdr.adventofcode.adventofcode2020.day12;

import java.util.Optional;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * Move the ship into a given direction.
 *
 * @author Martijn van de Rijdt
 */
record Move(Direction direction, int distance) implements Instruction {

    /**
     * Attempts to create a new move action, based on the given information.
     * 
     * @param letter representation of the direction, for example: 'N' for North
     * @param distance distance to move
     * @return move, or empty if the given letter does not represent a valid direction
     */
    static Optional<Instruction> of(char letter, int distance) {
        return Direction.of(letter)
                .map(direction -> new Move(direction, distance));
    }
    
    /** {@inheritDoc} */
    @Override
    public Ship execute(Ship startingPoint) {
        // Note: this implementation is linear in the distance.
        // This could be implemented in O(1) as well if performance becomes an issue.
        Point location = startingPoint.location();
        for (int i = 0; i != distance; i++) {
            location = direction.move(location);
        }
        return new Ship(location, startingPoint.direction());
    }
}
