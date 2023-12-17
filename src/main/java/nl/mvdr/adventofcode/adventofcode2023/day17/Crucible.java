package nl.mvdr.adventofcode.adventofcode2023.day17;

import java.util.Set;
import java.util.stream.Stream;

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
     * Determines the next possible steps for this crucible.
     * 
     * @param city the city, used to prevert the crucible from going out of bounds
     * @return the possible steps for this crucible to take
     */
    Stream<Crucible> possibleSteps(City city) {
        Set<Direction> newDirections;
        if (START.equals(this)) { // Special case: the only crucible without a predetermined direction
            newDirections = Set.of(Direction.RIGHT, Direction.DOWN);
        } else {
            newDirections = Set.of(direction, direction.turnClockwise(), direction.turnCounterClockwise());
        }
        return newDirections.stream()
                .map(this::step)
                .filter(crucible -> city.blocks().containsKey(crucible.location()))
                .filter(crucible -> crucible.steps <= 3);
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
