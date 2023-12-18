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
     * @return dig plan
     */
    static DigPlan parse(Stream<String> lines) {
        var instructions = lines.map(DigPlanInstruction::parse)
                .toList();
        return new DigPlan(instructions);
    }
    
    /**
     * Digs a trench according to this plan.
     * 
     * @return points making up the newly dug trench
     */
    List<Point> perform() {
        List<Point> result = new ArrayList<>();
        result.add(Point.ORIGIN);
        instructions.stream()
                .map(instruction -> instruction.perform(result.getLast()))
                .forEach(result::addAll);
        return result;
    }
}
