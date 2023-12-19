package nl.mvdr.adventofcode.adventofcode2023.day19;

import java.util.List;
import java.util.stream.Stream;

/**
 * A single rule.
 *
 * @author Martijn van de Rijdt
 */
sealed interface Rule permits ConditionalRule, AbsoluteRule {

    /**
     * Parses a list of rules.
     * 
     * @param text textual representation of a list of rules, for example: "x>10:one,m<20:two,a>30:R,A"
     * @return rules
     */
    static List<Rule> parse(String text) {
        var parts = text.split(",");
        return Stream.of(parts)
                .map(Rule::parseRule)
                .toList();
    }
    
    /**
     * Parses a rule.
     * 
     * @param text textual representation of a rule, for example: "x>10:one" or "A"
     * @return the rule
     */
    private static Rule parseRule(String text) {
        Rule result;
        if (text.contains(":")) {
            result = ConditionalRule.parse(text);
        } else {
            result = AbsoluteRule.parse(text);
        }
        return result;
    }
    
    /**
     * Checks this rule.
     * 
     * @param part part to apply this rule to
     * @return whether this rule's condition applies
     */
    boolean apply(Part part);
    
    /**
     * @return name of the target workflow, if this rule applies to a part
     */
    String target();
    
    /**
     * Determines to which subrange of the given part range this rule applies.
     * 
     * @param partRange part range
     * @return subrange
     */
    RangeFilterResult<PartRange> filter(PartRange partRange);
}
