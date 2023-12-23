package nl.mvdr.adventofcode.adventofcode2023.day23;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.Predicate;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * Representation of a hike.
 *
 * @param map the map
 * @param steps steps taken so far
 * @author Martijn van de Rijdt
 */
record Hike(HikingTrailsMap map, List<Point> steps) {
    
    /**
     * Convenienca constructor for the start of a hike.
     * 
     * @param map map
     */
    Hike(HikingTrailsMap map) {
        this(map, List.of(map.start()));
    }

    /** @return length of the longest hike from this point to the end goal */
    OptionalInt longestHikeLength() {
        OptionalInt result;
        if (isComplete()) {
            result = OptionalInt.of(steps.size());
        } else {
            result = step()
                    .map(Hike::longestHikeLength)
                    .filter(OptionalInt::isPresent)
                    .mapToInt(OptionalInt::orElseThrow)
                    .max();
        }
        return result;
    }
    
    /**
     * Takes a single step in any possible direction.
     * 
     * @return possible hikes
     */
    private Stream<Hike> step() {
        var currentLocation = getCurrentLocation();
        var currentTerrain = map.terrainMap().get(currentLocation);
        return Stream.of(Direction.UP, Direction.RIGHT, Direction.DOWN, Direction.LEFT)
                .filter(currentTerrain::canExit)
                .map(direction -> direction.move(currentLocation))
                .filter(map.terrainMap()::containsKey)
                .filter(Predicate.not(steps::contains))
                .map(this::addStep);
    }
    
    /**
     * Takes a single step.
     * 
     * @param newLocation new location
     * @return updated hike
     */
    private Hike addStep(Point newLocation) {
        List<Point> newSteps = new ArrayList<>(steps);
        newSteps.add(newLocation);
        return new Hike(map, newSteps);
    }
    
    /** @return current location */
    private Point getCurrentLocation() {
        return steps.getLast();
    }
    
    /** @return whether this hike ends at the goal */
    private boolean isComplete() {
        return getCurrentLocation().equals(map.goal());
    }
}
