package nl.mvdr.adventofcode.adventofcode2018.day20;

/**
 * "Regular" expression representing (part of) a map of rooms.
 *
 * @author Martijn van de Rijdt
 */
interface RoomMapExpression {
    // Marker interface
    
    /**
     * Parses the given string into a {@link RoomMapExpression}.
     * 
     * @param expression string representation of the expression to be parsed
     * @return expression
     */
    static RoomMapExpression parse(String expression) {
        // TODO implement
        return EmptyExpression.getInstance();
    }
}
