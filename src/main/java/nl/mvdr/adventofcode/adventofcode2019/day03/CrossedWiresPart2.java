package nl.mvdr.adventofcode.adventofcode2019.day03;

import java.util.ArrayList;
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
public class CrossedWiresPart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrossedWiresPart2.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return the fewest combined steps the wires must take to reach an intersection
     */
    @Override
    public int solve(Stream<String> lines) {
        List<List<Point>> wires = lines.filter(Predicate.not(String::isBlank))
                .map(this::parseWire)
                .collect(Collectors.toList());
        
        LOGGER.debug("Parsed {} wires", Integer.valueOf(wires.size())); // Should be 2
        
        Set<Point> intersections = new HashSet<>(wires.get(0));
        intersections.retainAll(Set.copyOf(wires.get(1)));

        LOGGER.debug("Found {} intersections", Integer.valueOf(intersections.size()));
        
        return intersections.stream()
                .mapToInt(point -> wires.get(0).indexOf(point) + wires.get(1).indexOf(point) + 2)
                .min()
                .getAsInt();
    }
    
    private List<Point> parseWire(String line) {
        List<Point> result = new ArrayList<>();
        
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
        CrossedWiresPart2 instance = new CrossedWiresPart2();

        String result = instance.solve("input-day03-2019.txt");

        LOGGER.info(result);
    }
}
