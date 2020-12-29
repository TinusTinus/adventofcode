package nl.mvdr.adventofcode.adventofcode2020.day19;

import java.util.Map;
import java.util.OptionalInt;

/**
 * Rule consisting of a choice between two subrules.
 *
 * @author Martijn van de Rijdt
 */
record OrRule(Rule lhs, Rule rhs) implements Rule {
    
    @Override
    public OptionalInt matchLength(String text, Map<Integer, Rule> rules) {
        OptionalInt result = lhs.matchLength(text, rules);
        if (result.isEmpty()) {
            result = rhs.matchLength(text, rules);
        }
        return result;
    }
}
