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
 * @param ultra whether this is an ultra crucible
 * @author Martijn van de Rijdt
 */
record Crucible(Point location, Direction direction, int steps, boolean ultra) {
    /**
     * Determines the next possible steps for this crucible.
     * 
     * @param city the city, used to prevert the crucible from going out of bounds
     * @return the possible steps for this crucible to take
     */
    Stream<CrucibleWithHeatLoss> possibleSteps(City city) {
        Set<Direction> newDirections;
        if (Point.ORIGIN.equals(location) && direction == null) {
            // Starting point
            newDirections = Set.of(Direction.RIGHT, Direction.DOWN);
        } else {
            newDirections = Set.of(direction, direction.turnClockwise(), direction.turnCounterClockwise());
        }
        return newDirections.stream()
                .map(newDirection -> step(newDirection, city))
                .filter(result -> city.blocks().containsKey(result.crucible().location()))
                .filter(result -> result.crucible().steps <= maximumSteps());
    }
    
    /**
     * @return minimum number of steps to move in any given direction without turning
     */
    private int minimumSteps() {
        int result;
        if (ultra) {
            result = 4;
        } else {
            result = 1;
        }
        return result;
    }
    
    /**
     * @return minimum number of steps to move in any given direction without turning
     */
    private int maximumSteps() {
        int result;
        if (ultra) {
            result = 10;
        } else {
            result = 3;
        }
        return result;
    }
    
    /**
     * Takes a single step in the given direction.
     * 
     * @param newDirection new direction to move in
     * @return updated step
     */
    private CrucibleWithHeatLoss step(Direction newDirection, City city) {
        int stepsToMove;
        int newSteps;
        if (newDirection == direction) {
            // Move one more step in the same direction.
            stepsToMove = 1;
            newSteps = steps + stepsToMove;
        } else {
            // We are changing direction. Move the minimum number of steps.
            stepsToMove = minimumSteps();
            newSteps = stepsToMove;
        }
        
        Point newLocation = location;
        int heatLoss = 0;
        for (int i = 0; i != stepsToMove; i++) {
            newLocation = newDirection.move(newLocation);
            if (city.blocks().containsKey(newLocation)) {
                heatLoss = heatLoss + city.blocks().get(newLocation).heatLoss();
            }
        }
        var newCrucible = new Crucible(newLocation, newDirection, newSteps, ultra);
        return new CrucibleWithHeatLoss(newCrucible, heatLoss);
    }
}
