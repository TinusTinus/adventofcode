package nl.mvdr.adventofcode.adventofcode2018.day06;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 6 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/6">Chronal Coordinates</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ChronalCoordinates implements PathSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ChronalCoordinates.class);
    
    @Override
    public String solve(Path inputFilePath) throws IOException {
        Set<Point> points = Point.parse(inputFilePath);

        // Construct a rectangle around all of the points
        int minX = points.stream().mapToInt(Point::getX).min().getAsInt() - 1;
        int maxX = points.stream().mapToInt(Point::getX).max().getAsInt() + 1;
        int minY = points.stream().mapToInt(Point::getY).min().getAsInt() - 1;
        int maxY = points.stream().mapToInt(Point::getY).max().getAsInt() + 1;
        
        // For each point in this rectangle, find the unique point from the input closest to it, according to its Manhattan distance.
        Map<Point, Optional<Point>> minimalDistances = 
                IntStream.range(minX, maxX + 1)
                        .mapToObj(Integer::valueOf)
                        .flatMap(x -> IntStream.range(minY, maxY + 1).mapToObj(y -> new Point(Integer.valueOf(x), y)))
                        .collect(Collectors.toMap(Function.identity(), point -> closest(point, points)));
        
        // TODO implement
        return null;
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
