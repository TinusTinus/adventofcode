package nl.mvdr.adventofcode.adventofcode2020.day12;

import java.util.Optional;

import nl.mvdr.adventofcode.point.Point;

/**
 * Moves the ship forward in its current direction.
 *
 * @author Martijn van de Rijdt
 */
record MoveForwardToWaypoint(int distance) implements Instruction {

    /**
     * Attempts to create a new move forward action, based on the given information.
     * 
     * @param letter representation of the action, should be 'F' for forward
     * @param distance distance to move
     * @return move forward action, or empty if the given letter does not represent a move forward action
     */
    static Optional<Instruction> of(char letter, int distance) {
        Optional<Instruction> result;
        if (letter == 'F') {
            result = Optional.of(new MoveForwardToWaypoint(distance));
        } else {
            result = Optional.empty();
        }
        return result;
    }
    
    /** {@inheritDoc} */
    @Override
    public Ship execute(Ship startingPoint) {
        Point location = startingPoint.location().translate(startingPoint.waypoint().times(distance));
        return new Ship(location, startingPoint.direction(), startingPoint.waypoint());
    }

}
