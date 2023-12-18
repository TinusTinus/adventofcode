package nl.mvdr.adventofcode.adventofcode2023.day18;

import java.util.List;
import java.util.stream.Stream;

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
}
