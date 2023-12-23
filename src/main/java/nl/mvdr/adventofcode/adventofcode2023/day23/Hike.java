package nl.mvdr.adventofcode.adventofcode2023.day23;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * Representation of a hike.
 *
 * @param map the map
 * @param visited steps taken so far
 * @author Martijn van de Rijdt
 */
record Hike(HikingTrailsMap map, List<Point> visited, boolean slipperySlopes) {
    
    /**
     * Convenienca constructor for the start of a hike.
     * 
     * @param map map
     * @param slipperySlopes whether the slopes are slippery
     */
    Hike(HikingTrailsMap map, boolean slipperySlopes) {
        this(map, List.of(map.start()), slipperySlopes);
    }

    /** @return length of the longest hike from this point to the end goal */
    int longestHikeLength() {
        Set<Hike> hikes = Set.of(this);
        var result = 0;
        var length = 0;
        while (!hikes.isEmpty()) {
            hikes = hikes.stream()
                    .flatMap(Hike::step)
                    .collect(Collectors.toSet());
            length++;
            if (hikes.stream().anyMatch(Hike::isComplete)) {
                result = length;
            }
        }
        
        if (result == 0) {
            throw new IllegalStateException("No path found.");
        }
        
        return result;
    }

    /** @return length of this hike, that is, the number of steps taken */
    int length() {
        return visited.size() - 1;
    }
    
    /**
     * Takes a single step in any possible direction.
     * 
     * @return possible hikes
     */
    private Stream<Hike> step() {
        
        // Observation: there are a lot of winding paths without any intersections.
        // Maybe we should cache these instead of going through the maze step-by-step every time?
        
        var currentLocation = getCurrentLocation();
        var currentTerrain = map.terrainMap().get(currentLocation);
        return Stream.of(Direction.UP, Direction.RIGHT, Direction.DOWN, Direction.LEFT)
                .filter(direction -> !slipperySlopes || currentTerrain.canExit(direction))
                .map(direction -> direction.move(currentLocation))
                .filter(map.terrainMap()::containsKey)
                .filter(Predicate.not(visited::contains))
                .map(this::addStep);
    }
    
    /**
     * Takes a single step.
     * 
     * @param newLocation new location
     * @return updated hike
     */
    private Hike addStep(Point newLocation) {
        List<Point> newSteps = new ArrayList<>(visited);
        newSteps.add(newLocation);
        return new Hike(map, newSteps, slipperySlopes);
    }
    
    /** @return current location */
    private Point getCurrentLocation() {
        return visited.getLast();
    }
    
    /** @return whether this hike ends at the goal */
    private boolean isComplete() {
        return getCurrentLocation().equals(map.goal());
    }
}
