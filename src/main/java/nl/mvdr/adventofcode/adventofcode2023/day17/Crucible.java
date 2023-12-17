package nl.mvdr.adventofcode.adventofcode2023.day17;

import java.util.Set;
import java.util.stream.Collectors;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * State of a crucible.
 *
 * @param location current city block occupied by the crucible
 * @param direction the direction the crucible was last traveling in; initially null
 * @param steps the number of steps taken in a row in this direction; may not exceed 3
 * @author Martijn van de Rijdt
 */
record Crucible(Point location, Direction direction, int steps) {
    /** Starting point, in the top-left, without any steps taken. */
    static final Crucible START = new Crucible(Point.ORIGIN, null, 0);
    
    /**
     * @return the possible steps for this crucible to take; note that these may be out-of-bounds of the city
     */
    Set<Crucible> possibleSteps() {
        Set<Direction> newDirections;
        if (START.equals(this)) {
            newDirections = Set.of(Direction.RIGHT, Direction.DOWN);
        } else {
            newDirections = Set.of(direction, direction.turnClockwise(), direction.turnCounterClockwise());
        }
        return newDirections.stream()
                .map(this::step)
                .filter(crucible -> crucible.steps <= 3)
                .collect(Collectors.toSet());
    }
    
    /**
     * Takes a single step in the given direction.
     * 
     * @param newDirection new direction to move in
     * @return updated step
     */
    private Crucible step(Direction newDirection) {
        var newLocation = newDirection.move(location);
        int newSteps;
        if (newDirection == direction) {
            newSteps = steps + 1;
        } else {
            newSteps = 1;
        }
        return new Crucible(newLocation, newDirection, newSteps);
    }
}
