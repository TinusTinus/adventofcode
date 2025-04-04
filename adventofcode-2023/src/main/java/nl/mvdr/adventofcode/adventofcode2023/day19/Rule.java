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
     * Determines to which subranges of the given part range this rule applies.
     * 
     * The filter result returned by this method contains two ranges:
     * one represents the values to which this rule does apply,
     * the other represents the values to which it does not.
     * The two ranges together make up the given range.
     * 
     * @param partRange part range
     * @return subranges
     */
    RangeFilterResult<PartRange> filter(PartRange partRange);
}
