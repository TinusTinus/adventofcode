package nl.mvdr.adventofcode.adventofcode2023.day21;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import nl.mvdr.adventofcode.point.Point;

/**
 * Map of the garden.
 *
 * @param cache cached results of {@link #findReachablePlots(int, Point)}; note that this cache is mutable!
 * @author Martijn van de Rijdt
 */
record GardenMap(Set<Point> gardenPlots, Point startingPosition, int width, int height, Map<StartingPointAndSteps, Set<Point>> cache) {
    
    private static final char ROCK_CHARACTER = '#';
    private static final char GARDEN_CHARACTER = '.';
    private static final char STARTING_POINT_CHARACTER = 'S';

    /**
     * Parses a garden map.
     * 
     * @param lines puzzle input
     * @return garden map
     */
    static GardenMap parse(List<String> lines) {
        var height = lines.size();
        var width = lines.get(0).length();
        
        Set<Point> gardenPlots = new HashSet<>();
        Set<Point> startingPoints = new HashSet<>();
        
        Point.parse2DMap(lines, (point, character) -> {
            if (character == STARTING_POINT_CHARACTER) {
                startingPoints.add(point);
                gardenPlots.add(point);
            } else if (character == GARDEN_CHARACTER) {
                gardenPlots.add(point);
            } else if (character != ROCK_CHARACTER) {
                throw new IllegalArgumentException("Invalid character found: " + character);
            }
        });
        
        if (startingPoints.size() != 1) {
            throw new IllegalArgumentException("No unique starting point found in input.");
        }
        var startingPoint = startingPoints.iterator().next();
        
        return new GardenMap(gardenPlots, startingPoint, width, height, new HashMap<>());
    }
    
    /**
     * Finds the plots that are reachable in exactly the given number of steps from the starting position.
     * 
     * @param steps number of steps
     * @return number of reachable plots
     */
    int countReachablePlots(int steps) {
        if (steps < 0) {
            throw new IllegalArgumentException("Negative steps not allowed: " + steps);
        }
        if (100 < steps) {
            throw new IllegalArgumentException("This solution does not perform well enough for this many steps :(");
        }
        var result = findReachablePlots(steps, startingPosition);
        return result.size();
    }

    /**
     * Finds the reachable garden plots, starting from the given starting point.
     * 
     * @param steps number of steps to take; must be non-negative
     * @param startingPoint starting garden plot
     * @return reachable plots
     */
    private Set<Point> findReachablePlots(int steps, Point startingPoint) {
        Set<Point> result = cache.get(new StartingPointAndSteps(startingPoint, steps));
        if (result == null) {
            var mod = startingPoint.floorMod(width, height);
            if (!startingPoint.equals(mod)) {
                // We are not in the "middle" (as in starting) copy of the garden.
                // Determine the offset.
                var offset = startingPoint.subtract(mod);
                result = findReachablePlots(steps, mod).stream()
                        .map(point -> point.add(offset))
                        .collect(Collectors.toSet());
            } else if (steps == 0) {
                result = Set.of(startingPoint);
            } else {
                result = startingPoint.neighbours()
                        .stream()
                        .filter(this::isGardenPlot)
                        .map(neighbour -> findReachablePlots(steps - 1, neighbour))
                        .flatMap(Set::stream)
                        .collect(Collectors.toSet());
            }
            cache.put(new StartingPointAndSteps(startingPoint, steps), result);
        }
        return result;
    }
    
    /**
     * Checks whether the given point is a garden plot.
     * 
     * @param point point
     * @param infiniteGarden whether the garden stretches out infinitely
     * @return whether the given point is a garden plot
     */
    private boolean isGardenPlot(Point point) {
        return gardenPlots.contains(point.floorMod(width, height));
    }
}
