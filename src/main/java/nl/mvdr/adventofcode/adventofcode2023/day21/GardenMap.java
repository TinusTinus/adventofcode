package nl.mvdr.adventofcode.adventofcode2023.day21;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.IntStream;

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
        
        return (int) IntStream.range(0, steps + 1)
                .filter(i -> i % 2 == steps % 2)
                .mapToObj(startingPosition::pointsAtManhattanDistance)
                .flatMap(Function.identity())
                .filter(this::isGardenPlot)
                .count();
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
