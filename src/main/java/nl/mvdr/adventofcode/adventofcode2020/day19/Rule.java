package nl.mvdr.adventofcode.adventofcode2020.day19;

import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Representation of a (sub)rule.
 *
 * @author Martijn van de Rijdt
 */
interface Rule {
    
    /**
     * Parses rules from the puzzle input.
     *  
     * @param lines lines from the puzzle input; each line should contain a textual representation of a rule, including its number;
     *      for example: {@code 0: 1 2 | 3 4} or {@code 1: "a"}
     * @return rules, indexed by their numbers
     */
    static Map<Integer, Rule> parseRules(List<String> lines) {
        return lines.stream()
                .map(line -> line.split(": "))
                .collect(Collectors.toMap(parts -> Integer.valueOf(parts[0]), parts -> parseRule(parts[1])));
    }
    
    /**
     * Parses a textual representation of a rule.
     * 
     * @param text the textual representation of a rule, without the rule's number;
     *      for example: {@code "a"} or {@code 1 2 | 3 4}
     * @return rule represented by the given text
     */
    private static Rule parseRule(String text) {
        Rule result;
        if (text.startsWith("\"")) {
            result = new LetterRule(text.charAt(1));
        } else {
            String parts[] = text.split(" \\| ");
            if (parts.length == 1) {
                List<Integer> subrules = Stream.of(text.split(" "))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                result = new SequenceRule(subrules);
            } else if (parts.length == 2) {
                Rule lhs = parseRule(parts[0]);
                Rule rhs = parseRule(parts[1]);
                result = new OrRule(lhs, rhs);
            } else {
                throw new IllegalArgumentException("Unable to parse (sub)rule: " + text);
            }
        }
        return result;
    }
    
    /**
     * Determines whether the given text matches this rule.
     * 
     * @param text text to match
     * @param rules map of all rules, indexed by their rule number
     * @return whether the text matches the rule
     */
    default boolean matches(String text, Map<Integer, Rule> rules) {
        return matchLength(text, rules).orElse(-1) == text.length();
    }
    
    /**
     * Determines whether a prefix of the given text matches this rule
     * and, if so, returns the (unique!) length of the matching prefix.
     * 
     * @param text text to match
     * @param rules map of all rules, indexed by their rule number
     * @return length of the matching prefix
     */
    OptionalInt matchLength(String text, Map<Integer, Rule> rules);
}
