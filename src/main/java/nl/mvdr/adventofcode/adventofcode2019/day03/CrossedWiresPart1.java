package nl.mvdr.adventofcode.adventofcode2019.day03;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;
import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * Solution to the day 3 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/3">Crossed Wires</a>.
 *
 * @author Martijn van de Rijdt
 */
public class CrossedWiresPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrossedWiresPart1.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return the Manhattan distance from the central port to the closest intersection
     */
    @Override
    public int solve(Stream<String> lines) {
        List<Set<Point>> wires = lines.filter(Predicate.not(String::isBlank))
                .map(this::parseWire)
                .collect(Collectors.toList());
        
        Set<Point> intersections = new HashSet<>(wires.get(0));
        intersections.retainAll(wires.get(1));
        
        return intersections.stream()
                .mapToInt(Point::manhattanDistanceToOrigin)
                .min()
                .getAsInt();
    }
    
    private Set<Point> parseWire(String line) {
        Set<Point> result = new HashSet<>();
        
        Point point = new Point(0, 0);
        
        String[] segments = line.split(",");
        for (String segment : segments) {
            Direction direction = Direction.of(segment.charAt(0)).orElseThrow();
            int steps = Integer.parseInt(segment.substring(1));
            for (int step = 0; step != steps; step++) {
                point = direction.move(point);
                result.add(point);
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
        CrossedWiresPart1 instance = new CrossedWiresPart1();

        String result = instance.solve("input-day03-2019.txt");

        LOGGER.info(result);
    }
}
