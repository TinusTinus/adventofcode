package nl.mvdr.adventofcode.adventofcode2023.day19;

/**
 * A single conditional rule.
 *
 * @author Martijn van de Rijdt
 */
record ConditionalRule(Property property, Operand operand, int value, String target) implements Rule {
    /**
     * Parses a rule.
     * 
     * @param text textual representation of a rule, for example: "x>10:one"
     * @return the rule
     */
    static ConditionalRule parse(String text) {
        var property = Property.parse(text.substring(0, 1));
        var operand = Operand.parse(text.substring(1, 2));
        var remainingParts = text.substring(2).split(":");
        if (remainingParts.length != 2) {
            throw new IllegalArgumentException("Unable to parse rule: " + text);
        }
        var value = Integer.parseInt(remainingParts[0]);
        var target = remainingParts[1];
        
        return new ConditionalRule(property, operand, value, target);
    }

    @Override
    public boolean apply(Part part) {
        var propertyValue = part.properties().get(property).intValue();
        return operand.apply(propertyValue, value);
    }
}
