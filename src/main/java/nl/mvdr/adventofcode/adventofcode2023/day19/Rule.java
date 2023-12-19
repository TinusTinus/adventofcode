package nl.mvdr.adventofcode.adventofcode2023.day19;

/**
 * A single rule.
 *
 * @author Martijn van de Rijdt
 */
sealed interface Rule permits ConditionalRule, AbsoluteRule {

    /**
     * Parses a rule.
     * 
     * @param text textual representation of a rule, for example: "x>10:one" or "A"
     * @return the rule
     */
    static Rule parse(String text) {
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
}
