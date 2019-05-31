package nl.mvdr.adventofcode.adventofcode2018.day06;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 6 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/6">Chronal Coordinates</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ChronalCoordinatesPart2 implements PathSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ChronalCoordinatesPart2.class);
    
    private final int maxTotalDistance;
    
    /**
     * Constructor.
     * 
     * @param maxTotalDistance maximum total Manhattan distance to the set of points
     */
    private ChronalCoordinatesPart2(int maxTotalDistance) {
        super();
        this.maxTotalDistance = maxTotalDistance;
    }

    /** Default constructor, with the maximum distance from the example. */
    public ChronalCoordinatesPart2() {
        this(32);
    }
    
    @Override
    public String solve(Path inputFilePath) throws IOException {
        Set<Point> points = Point.parse(inputFilePath);

        // Find a point within range. (Technically not guaranteed to exist!)
        Point startingPoint = points.stream()
                .filter(point -> point.totalManhattanDistance(points) < maxTotalDistance)
                .findFirst()
                .get();
        
        Set<Point> area = new HashSet<>(Set.of(startingPoint));
        Set<Point> candidates = new HashSet<>(startingPoint.neighbours());
        
        while(!candidates.isEmpty()) {
            Point candidate = candidates.iterator().next();
            candidates.remove(candidate);
            
            if (candidate.totalManhattanDistance(points) < maxTotalDistance) {
                area.add(candidate);
                candidate.neighbours().stream()
                    .filter(neighbour -> !area.contains(neighbour))
                    .forEach(candidates::add);
            }
        }
        
        return area.size() + "";
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ChronalCoordinatesPart2 instance = new ChronalCoordinatesPart2(10_000);

        String result = instance.solve("input-day06-2018.txt");

        LOGGER.info(result);
    }
}
