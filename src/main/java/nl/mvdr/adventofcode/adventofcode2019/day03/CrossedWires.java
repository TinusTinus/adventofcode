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
abstract class CrossedWires implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrossedWires.class);
    
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
        List<Point> wire0 = wires.get(0);
        List<Point> wire1 = wires.get(1);        
        
        Set<Point> intersections = new HashSet<>(wire0);
        intersections.retainAll(Set.copyOf(wire1));

        LOGGER.debug("Found {} intersections", Integer.valueOf(intersections.size()));
        
        return intersections.stream()
                .filter(point -> !(new Point(0, 0)).equals(point))
                .mapToInt(point -> computeDistance(point, wire0, wire1))
                .min()
                .getAsInt();
    }
    
    /**
     * Parses a line from the input.
     * 
     * @param line textual representation of a wire, for example: "R8,U5,L5,D3"
     * @return the wire's path, represented as a list of twodimensional points
     */
    private List<Point> parseWire(String line) {
        List<Point> result = new ArrayList<>();
        
        Point point = new Point(0, 0);
        result.add(point);
        
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
     * Computes the distance to the given point.
     * 
     * @param point point
     * @param wire0 first wire
     * @param wire1 second wire
     * @return distance to {@code point}
     */
    abstract int computeDistance(Point point, List<Point> wire0, List<Point> wire1);
}
