package nl.mvdr.adventofcode.adventofcode2023.day18;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
     * @return dig plan
     */
    static DigPlan parse(Stream<String> lines) {
        var instructions = lines.map(DigPlanInstruction::parse)
                .toList();
        return new DigPlan(instructions);
    }
    
    /**
     * Digs a hole according to this plan.
     * 
     * @return points making up the hole
     */
    Set<Point> digHole() {
        var trench = digTrench();
        return digHole(trench);
    }
    
    /**
     * Digs a trench according to this plan.
     * 
     * @return points making up the newly dug trench
     */
    private List<Point> digTrench() {
        List<Point> result = new ArrayList<>();
        result.add(Point.ORIGIN);
        instructions.stream()
                .map(instruction -> instruction.dig(result.getLast()))
                .forEach(result::addAll);
        return result;
    }
    
    /**
     * Digs out the inside of the given trench.
     * 
     * @param trench trench
     * @return hole
     */
    private Set<Point> digHole(List<Point> trench) {
        return new HashSet<>(trench); // TODO implement!
    }
}
