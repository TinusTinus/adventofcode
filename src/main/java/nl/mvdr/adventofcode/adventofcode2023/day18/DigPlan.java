package nl.mvdr.adventofcode.adventofcode2023.day18;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.point.Point;

/**
 * A dig plan.
 *
 * @author Martijn van de Rijdt
 */
record DigPlan(List<DigPlanInstruction> instructions) {
    
    /**
     * Parses a dig plan.
     * 
     * @param lines puzzle input
     * @param hexValueAsColor whether hex values should be interpreted as colors
     * @return dig plan
     */
    static DigPlan parse(Stream<String> lines, boolean hexValueAsColor) {
        var instructions = lines.map(line -> DigPlanInstruction.parse(line, hexValueAsColor))
                .toList();
        return new DigPlan(instructions);
    }
    
    /**
     * @return size of the hole specified by this dig plan
     */
    long holeSize() {
        var nodes = digTrench(); // TODO take into account that the trench itself is one meter wide!
        return (long) computeArea(nodes);
    }
    
    /**
     * Digs a trench according to this plan.
     * 
     * @return nodes in the trench
     */
    private List<Point> digTrench() {
        List<Point> result = new ArrayList<>();
        result.add(Point.ORIGIN);
        instructions.stream()
                .map(instruction -> instruction.dig(result.getLast()))
                .forEach(result::add);
        return result;
    }
    
    /**
     * Computes the area of a polygon as defined by the given list of nodes.
     * 
     * Uses the so-called <a href="https://en.wikipedia.org/wiki/Shoelace_formula">shoelace formula</a>.
     * 
     * @param points points defining the polygon
     * @return area of the polygon
     */
    private static double computeArea(List<Point> points) {
        long area = 0L;

        for (var i = 0; i < points.size(); i++) {
            var point = points.get(i);
            var nextPoint = points.get((i + 1) % points.size());
            
            var pointX = (long) point.x();
            var pointY = (long) point.y();
            var nextPointX = (long) nextPoint.x();
            var nextPointY = (long) nextPoint.y();
            
            area += pointX * nextPointY - nextPointX * pointY;
        }

        return area / 2.0;
    }
}
