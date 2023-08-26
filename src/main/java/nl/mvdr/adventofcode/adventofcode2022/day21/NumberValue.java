package nl.mvdr.adventofcode.adventofcode2022.day21;

/**
 * A numeric value as yelled by a monkey.
 *
 * @author Martijn van de Rijdt
 */
record NumberValue(int number) implements Value {
    
    /**
     * Parses the string representation of a numberic value.
     * 
     * @param stringRepresentation string representation
     * @return number value
     */
    static NumberValue parse(String stringRepresentation) {
        return new NumberValue(Integer.parseInt(stringRepresentation));
    }
}
