package nl.mvdr.adventofcode.adventofcode2020.day19;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;

/**
 * Rule consisting of a sequence of subrules.
 *
 * @author Martijn van de Rijdt
 */
record SequenceRule(List<Integer> subrules) implements Rule {

    /** {@inheritDoc} */
    @Override
    public OptionalInt matchLength(String text, Map<Integer, Rule> rules) {
        OptionalInt result = OptionalInt.of(0);
        for (int i = 0; result.isPresent() && i != subrules.size(); i++) {
            Rule subrule = rules.get(subrules.get(i));
            OptionalInt subruleMatchLength = subrule.matchLength(text.substring(result.orElseThrow()), rules);
            if (subruleMatchLength.isPresent()) {
                result = OptionalInt.of(result.orElseThrow() + subruleMatchLength.orElseThrow());
            } else {
                result = OptionalInt.empty();
            }
        }
        return result;
    }

}
