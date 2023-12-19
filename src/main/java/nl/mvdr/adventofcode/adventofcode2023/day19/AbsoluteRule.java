package nl.mvdr.adventofcode.adventofcode2023.day19;

/**
 * A rule which always applies, to any part.
 *
 * @author Martijn van de Rijdt
 */
record AbsoluteRule(String target) implements Rule {

    /**
     * Parses a rule.
     * 
     * @param text textual representation of a rule, for example: "A"
     * @return the rule
     */
    static AbsoluteRule parse(String text) {
        return new AbsoluteRule(text);
    }
    
    @Override
    public boolean apply(Part part) {
        return true;
    }

    @Override
    public RuleFilterResult filter(PartRange partRange) {
        // Applies to any part
        return new RuleFilterResult(partRange, PartRange.EMPTY_RANGE);
    }
}
