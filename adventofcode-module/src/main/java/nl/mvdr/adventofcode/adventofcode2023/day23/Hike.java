package nl.mvdr.adventofcode.adventofcode2023.day23;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Representation of a hike.
 *
 * @param visited points visited so far
 * @author Martijn van de Rijdt
 */
record Hike(List<PointOfInterest> visited) {
    
    /**
     * Convenienca constructor for the start of a hike.
     * 
     * @param map map
     * @param slipperySlopes whether the slopes are slippery
     */
    Hike(PointOfInterest start) {
        this(List.of(start));
    }

    /** @return length of this hike, that is, the number of steps taken */
    int length() {
        return IntStream.range(0, visited.size() - 1)
                .map(i -> visited.get(i).pathLengths().get(visited.get(i + 1)).intValue())
                .sum();
    }
    
    /**
     * Moves in any possible direction.
     * 
     * @param pointsOfInterest set containing all points of interest
     * @return possible hikes
     */
    Stream<Hike> step() {
        return visited.getLast()
                .pathLengths()
                .keySet()
                .stream()
                .filter(Predicate.not(visited::contains))
                .map(this::addStep);
    }
    
    /**
     * Moves directly to the given point of interest.
     * 
     * @param newLocation new location
     * @return updated hike
     */
    private Hike addStep(PointOfInterest newLocation) {
        List<PointOfInterest> newVisited = new ArrayList<>(visited);
        newVisited.add(newLocation);
        return new Hike(newVisited);
    }
    
    /**
     * Checks whether this hike ends at the given point.
     * 
     * @param goal the goal
     * @return whether this hike ends at the given point
     */
    boolean endsAt(PointOfInterest goal) {
        return visited.getLast().equals(goal);
    }
}
