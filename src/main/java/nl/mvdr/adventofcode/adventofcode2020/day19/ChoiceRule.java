package nl.mvdr.adventofcode.adventofcode2020.day19;

import java.util.Map;
import java.util.OptionalInt;

/**
 * Rule consisting of a choice between two subrules.
 *
 * @author Martijn van de Rijdt
 */
record ChoiceRule(Rule lhs, Rule rhs) implements Rule {
    
    @Override
    public OptionalInt matchLength(String text, Map<Integer, Rule> rules) {
        // TODO this implementation assumes that if lhs matches, rhs does not need to be inspected any more; this is incorrect for part 2!
        OptionalInt result = lhs.matchLength(text, rules);
        if (result.isEmpty()) {
            result = rhs.matchLength(text, rules);
        }
        return result;
    }
}
