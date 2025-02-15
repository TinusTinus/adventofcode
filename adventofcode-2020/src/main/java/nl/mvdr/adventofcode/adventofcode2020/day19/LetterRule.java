package nl.mvdr.adventofcode.adventofcode2020.day19;

import java.util.Map;
import java.util.Set;

/**
 * A rule which matches a single letter.
 *
 * @author Martijn van de Rijdt
 */
public record LetterRule(char letter) implements Rule {

    /** {@inheritDoc} */
    @Override
    public Set<Integer> matchingPrefixLengths(String text, Map<Integer, Rule> rules) {
        Set<Integer> result;
        if (text.startsWith(Character.toString(letter))) {
            result = Set.of(Integer.valueOf(1));
        } else {
            result = Set.of();
        }
        return result;
    }
}
