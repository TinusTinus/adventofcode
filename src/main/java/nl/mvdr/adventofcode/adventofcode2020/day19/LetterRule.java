package nl.mvdr.adventofcode.adventofcode2020.day19;

import java.util.Map;
import java.util.OptionalInt;

/**
 * A rule which matches a single letter.
 *
 * @author Martijn van de Rijdt
 */
public record LetterRule(char letter) implements Rule {

    /** {@inheritDoc} */
    @Override
    public OptionalInt matchLength(String text, Map<Integer, Rule> rules) {
        OptionalInt result;
        if (text.startsWith(Character.toString(letter))) {
            result = OptionalInt.of(1);
        } else {
            result = OptionalInt.empty();
        }
        return result;
    }
}
