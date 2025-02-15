package nl.mvdr.adventofcode.adventofcode2022.day21;

/**
 * A value yelled by a monkey.
 *
 * @author Martijn van de Rijdt
 */
interface Value {
    
    /**
     * Parses a monkey's value.
     * 
     * @param stringRepresentation string representation of the value
     * @return value
     */
    static Value parse(String stringRepresentation) {
        Value result;
        if (stringRepresentation.contains(" ")) {
            result = ExpressionValue.parse(stringRepresentation);
        } else {
            result = NumberValue.parse(stringRepresentation);
        }
        return result;
    }
    
}
