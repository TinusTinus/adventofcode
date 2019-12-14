package nl.mvdr.adventofcode.adventofcode2018.day06;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;
import nl.mvdr.adventofcode.point.Point;

/**
 * Solution to the day 6 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/6">Chronal Coordinates</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ChronalCoordinates implements IntSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ChronalCoordinates.class);
    
    @Override
    public int solve(Stream<String> lines) {
        Set<Point> points = Point.parse(lines);

        // Construct a rectangle surrounding all of the points
        int minX = Point.minX(points) - 1;
        int maxX = Point.maxX(points) + 1;
        int minY = Point.minY(points) - 1;
        int maxY = Point.maxY(points) + 1;
        
        // For each point in this rectangle, find the unique point from the input closest to it, according to its Manhattan distance.
        Map<Point, Optional<Point>> minimalDistances = 
                IntStream.range(minX, maxX + 1)
                        .boxed()
                        .flatMap(x -> IntStream.range(minY, maxY + 1).mapToObj(y -> new Point(x.intValue(), y)))
                        .collect(Collectors.toMap(Function.identity(), point -> closest(point, points)));
        
        return points.stream()
                .map(Optional::of)
                .map(point -> minimalDistances.entrySet().stream()
                        .filter(entry -> point.equals(entry.getValue()))
                        .map(Entry::getKey)
                        .collect(Collectors.toSet()))
                .filter(area -> isFinite(area, minX, minY, maxX, maxY))
                .mapToInt(Set::size)
                .max()
                .getAsInt();
    }
    
    /**
     * Determines whether the given area is finite.
     * 
     * @param area area
     * @param minX minimum x coordinate of the rectangle surrounding all of the points
     * @param minY minimum y coordinate of the rectangle surrounding all of the points
     * @param maxX maximum x coordinate of the rectangle surrounding all of the points
     * @param maxY maximum y coordinate of the rectangle surrounding all of the points
     * @return whether the area is finite
     */
    private boolean isFinite(Set<Point> area, int minX, int minY, int maxX, int maxY) {
        return area.stream().allMatch(point -> point.getX() != minX && point.getX() != maxX && point.getY() != minY && point.getY() != maxY);
    }
    
    
    /**
     * Given a point, this method determines the closest element of the given set of points, according to the Manhattan distance.
     * 
     * @param point point
     * @param points points
     * @return the element of points which is closest to the given point; empty in case the result is not unique
     */
    private Optional<Point> closest(Point point, Set<Point> points) {
        int minDistance = Integer.MAX_VALUE;
        Optional<Point> result = Optional.empty();
        
        for (Point p : points) {
            int distance = point.manhattanDistance(p);
            if (distance < minDistance) {
                minDistance = distance;
                result = Optional.of(p);
            } else if (distance == minDistance) {
                result = Optional.empty();
            }
        }
        return result;
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ChronalCoordinates instance = new ChronalCoordinates();

        String result = instance.solve("input-day06-2018.txt");

        LOGGER.info(result);
    }
}
