package nl.mvdr.adventofcode.adventofcode2023.day19;

/**
 * A single conditional rule.
 *
 * @author Martijn van de Rijdt
 */
record Rule(Property property, Operand operand, int value, String target) {
    /**
     * Parses a rule.
     * 
     * @param text textual representation of a rule, for example: "x>10:one"
     * @return the rule
     */
    static Rule parse(String text) {
        var property = Property.parse(text.substring(0, 1));
        var operand = Operand.parse(text.substring(1, 2));
        var remainingParts = text.substring(2).split(":");
        if (remainingParts.length != 2) {
            throw new IllegalArgumentException("Unable to parse rule: " + text);
        }
        var value = Integer.parseInt(remainingParts[0]);
        var target = remainingParts[1];
        
        return new Rule(property, operand, value, target);
    }

    /**
     * Checks this rule.
     * 
     * @param part part to apply this rule to
     * @return whether this rule's condition applies
     */
    boolean apply(Part part) {
        var propertyValue = part.properties().get(property).intValue();
        return operand.apply(propertyValue, value);
    }
}
