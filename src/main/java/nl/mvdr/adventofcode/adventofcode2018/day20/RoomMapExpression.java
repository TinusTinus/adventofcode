package nl.mvdr.adventofcode.adventofcode2018.day20;

import java.util.Set;

import nl.mvdr.adventofcode.adventofcode2018.point.Point;

/**
 * "Regular" expression representing (part of) a map of rooms.
 *
 * @author Martijn van de Rijdt
 */
interface RoomMapExpression {
    
    /**
     * Generates (part of) the map based on this expression.
     * 
     * @param points current possible locations, which can be reached up until this subexpression
     * @param map partially complete map; this method may add more rooms and/or doors 
     * @return possible locations, which can be reached by the part of the expression processed up until and including this subexpression
     */
    Set<Point> apply(Set<Point> points, RoomMap map);
    
    /**
     * Parses the given string into a {@link RoomMapExpression}.
     * 
     * @param expression string representation of the expression to be parsed
     * @return expression
     */
    static RoomMapExpression parse(String expression) {
        RoomMapExpression result;
        if (expression.isEmpty()) {
            result = EmptyExpression.getInstance();
        } else {
            
            char firstCharacter = expression.charAt(0);

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
                while (nextCharacter != '|' || 1 < nest) {
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

                while (nextCharacter != ')' || 1 < nest) {
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
                RoomMapExpression branch = new Branch(lhs, rhs);
                RoomMapExpression remaining = parse(expression.substring(closingBracketIndex + 1));
                result = new Concatenation(branch, remaining);
            } else {
                // Direction.
                Direction direction = Direction.parse(firstCharacter);
                result = new Concatenation(direction, parse(expression.substring(1)));
            }
        }
        
        return result;
    }
}
