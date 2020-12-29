package nl.mvdr.adventofcode.adventofcode2020.day19;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Rule consisting of a sequence of subrules.
 *
 * @author Martijn van de Rijdt
 */
record SequenceRule(List<Integer> subrules) implements Rule {

    /** {@inheritDoc} */
    @Override
    public Set<Integer> matchingPrefixLengths(String text, Map<Integer, Rule> rules) {
        Set<Integer> result = Set.of(Integer.valueOf(0));
        for (int i = 0; i != subrules.size(); i++) {
            Set<Integer> newResult = new HashSet<>();
            Rule subrule = rules.get(subrules.get(i));
            
            for (Integer prefixLength : result) {
                Set<Integer> subruleMatchLengths = subrule.matchingPrefixLengths(text.substring(prefixLength.intValue()), rules);
                for (Integer subruleMatchLength : subruleMatchLengths) {
                    newResult.add(Integer.valueOf(prefixLength.intValue() + subruleMatchLength.intValue()));
                }
            }
            result = newResult;
        }
        return result;
    }

}
