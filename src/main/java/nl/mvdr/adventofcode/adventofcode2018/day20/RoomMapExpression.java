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
            // Search for the pipe |.
            char nextCharacter = expression.charAt(1);
            int pipeIndex = 1;
            int nest = 1;
            while(nextCharacter != '|' || 1 < nest) {
                if (nextCharacter == '(') {
                    nest++;
                } else if (nextCharacter == ')') {
                    nest--;
                }
                
                nextCharacter = expression.charAt(pipeIndex + 1);
                pipeIndex++;
            }
            // Found the pipe '|'.
            // Search for the closing bracket ')'.
            nextCharacter = expression.charAt(pipeIndex + 1);
            int closingBracketIndex = pipeIndex + 1;
            
            while (0 < nest) {
                if (nextCharacter == '(') {
                    nest++;
                } else if (nextCharacter == ')') {
                    nest--;
                }
                
                nextCharacter = expression.charAt(closingBracketIndex + 1);
                closingBracketIndex++;
            }
            
            // Found the pipe '|' and the closing bracket ')'.
            RoomMapExpression lhs = parse(expression.substring(1, pipeIndex));
            RoomMapExpression rhs = parse(expression.substring(pipeIndex + 1, closingBracketIndex));
            result = new Branch(lhs, rhs);
        } else {
            // Direction.
            Direction direction = Direction.parse(firstCharacter);
            result = new Concatenation(direction, parse(expression.substring(1)));
        }
        
        return result;
    }
}
