package nl.mvdr.adventofcode.adventofcode2023.day21;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    long countReachablePlots(int steps) {
        // I struggled with this puzzle so I ended up taking inspiration from Reddit
        // (https://www.reddit.com/r/adventofcode/comments/18nevo3/2023_day_21_solutions/).
        // Turns out we once again need(?) additional assumptions from observing the input data.
        // The following part 2 implementation was adapted from the one by Smooth-Aide-1751: https://pastebin.com/DyYZVfHx
        // Works for the actual input (but not part 1 / example puzzles)
        
        var visited = new HashSet<Point>();
        var frontier = new HashSet<Point>();
        frontier.add(startingPosition);
 
        long[] counts = new long[3];
 
        int[] frontiers = new int[width];
        int[] d1 = new int[width];
        int[] d2 = new int[width];
 
        int step = 0;
        while (true) {
 
            var newFrontier = new HashSet<Point>();
            for (var p : frontier) {
                for (var q : p.neighbours()) {
                    if (isGardenPlot(q) && visited.add(q)) {
                        newFrontier.add(q);
                    }
                }
            }
 
            int fsize = newFrontier.size();
            counts[2] = fsize + counts[0];
            counts[0] = counts[1];
            counts[1] = counts[2];
 
            int ix = step % width;
            if (step >= width) {
                int dx = fsize - frontiers[ix];
                d2[ix] = dx - d1[ix];
                d1[ix] = dx;
            }
            frontiers[ix] = fsize;
 
            frontier = newFrontier;
            step++;
 
            if (step >= 2*width) {
                if (Arrays.stream(d2).allMatch(i -> i == 0)) {
                    break;
                }
            }
        }
 
        System.out.println("step: " + step);
        System.out.println(counts[2]);
        System.out.println(Arrays.toString(frontiers));
        System.out.println(Arrays.toString(d1));
        System.out.println(Arrays.toString(d2));
 
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
