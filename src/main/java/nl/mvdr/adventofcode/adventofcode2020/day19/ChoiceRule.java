package nl.mvdr.adventofcode.adventofcode2020.day19;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Rule consisting of a choice between two subrules.
 *
 * @author Martijn van de Rijdt
 */
record ChoiceRule(Rule lhs, Rule rhs) implements Rule {
    
    @Override
    public Set<Integer> matchingPrefixLengths(String text, Map<Integer, Rule> rules) {
        Set<Integer> result = new HashSet<>();
        result.addAll(lhs.matchingPrefixLengths(text, rules));
        result.addAll(rhs.matchingPrefixLengths(text, rules));
        return result;
    }
}
