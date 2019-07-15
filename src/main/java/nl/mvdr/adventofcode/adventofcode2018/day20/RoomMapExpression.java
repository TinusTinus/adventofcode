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
        
        char firstCharacter = expression.charAt(0);
        
        RoomMapExpression result;
        if (firstCharacter == '^') {
            // Start of the expression.
            result = new CompleteExpression(parse(expression.substring(1)));
        } else if (firstCharacter == '$') {
            // End of the expression.
            result = EmptyExpression.getInstance();
        } else if (firstCharacter == '(') {
            // Start of a branch.
            // TODO something complicated
            result = EmptyExpression.getInstance();
        } else {
            // Direction, followed by other stuff.
            Direction direction = Direction.parse(firstCharacter);
            result = new Concatenation(direction, parse(expression.substring(1)));
        }
        
        return result;
    }
}
