package nl.mvdr.adventofcode.adventofcode2023.day21;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import nl.mvdr.adventofcode.point.Point;

/**
 * Map of the garden.
 *
 * @param cache cached results of {@link #findReachablePlots(int, Point)}; note that this cache is mutable!
 * @author Martijn van de Rijdt
 */
record GardenMap(Set<Point> gardenPlots, Point startingPosition, int width, int height) {
    
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
        
        return new GardenMap(gardenPlots, startingPoint, width, height);
    }
    
    /**
     * Finds the plots that are reachable in exactly the given number of steps from the starting position.
     * 
     * @param steps number of steps
     * @return number of reachable plots
     */
    long countReachablePlots(int steps) {
        long result;
        if (steps < 71) { // TODO this is the number for the example input; can we determine this based on the map itself?
            // Just use the straightforward, naive solution
            result = countReachablePlotsNaively(steps);
        } else {
            result = countReachablePlotsUsingExtrapolation(steps);
        }
        return result;
    }

    /**
     * Finds the plots that are reachable in exactly the given number of steps from the starting position.
     * 
     * This is a naive solution which does not scale well to large numbers of steps.
     * 
     * @param steps number of steps
     * @return number of reachable plots
     */
    private long countReachablePlotsNaively(int steps) {
        var result = Set.of(startingPosition);
        for (var i = 0; i != steps; i++) {
            result = result.stream()
                    .map(Point::neighbours)
                    .flatMap(Set::stream)
                    .filter(point -> isGardenPlot(point))
                    .collect(Collectors.toSet());
        }
        return result.size();
    }    

    /**
     * Finds the plots that are reachable in exactly the given number of steps from
     * the starting position, using extrapolation to get the correct result for
     * large numbers of steps.
     * 
     * I struggled with this puzzle, so I ended up taking inspiration from <a href=
     * "https://www.reddit.com/r/adventofcode/comments/18nevo3/2023_day_21_solutions/">Reddit</a>.
     * Turns out we once again need(?) additional assumptions from observing the
     * input data. The following implementation was adapted from the
     * <a href="https://pastebin.com/DyYZVfHx">part 2 solution</a> by
     * <a href="https://www.reddit.com/user/Smooth-Aide-1751/">Smooth-Aide-1751</a>.
     * 
     * @param steps number of steps
     * @return number of reachable plots
     */
    private long countReachablePlotsUsingExtrapolation(int steps) {
        // Keep a set of visited points (to prevent investigating the same point multiple times)
        Set<Point> visited = new HashSet<>();
        
        // Keep track of the frontier: newly visited points
        var frontier = Set.of(startingPosition);
 
        long[] counts = new long[3];
 
        int[] frontiers = new int[width];
        int[] d1 = new int[width];
        int[] d2 = new int[width];
 
        int step = 0;
        while (!(2 * width <= step && Arrays.stream(d2).allMatch(i -> i == 0))) {
            frontier = frontier.stream()
                    .map(Point::neighbours)
                    .flatMap(Set::stream)
                    .filter(this::isGardenPlot)
                    .filter(visited::add)
                    .collect(Collectors.toSet());
 
            counts[2] = frontier.size() + counts[0];
            counts[0] = counts[1];
            counts[1] = counts[2];
 
            int ix = step % width;
            if (width <= step) {
                int dx = frontier.size() - frontiers[ix];
                d2[ix] = dx - d1[ix];
                d1[ix] = dx;
            }
            frontiers[ix] = frontier.size();
 
            step++;
        }
 
        // extrapolate
        for (int i = step; i < steps; i++) {
            int ix = i % width;
            d1[ix] += d2[ix];
            frontiers[ix] += d1[ix];
 
            counts[2] = counts[0] + frontiers[ix];
            counts[0] = counts[1];
            counts[1] = counts[2];
        }
 
        return counts[2];
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
