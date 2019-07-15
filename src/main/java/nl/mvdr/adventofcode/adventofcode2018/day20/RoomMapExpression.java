package nl.mvdr.adventofcode.adventofcode2018.day20;

import java.util.ArrayList;
import java.util.List;
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
                List<RoomMapExpression> options = new ArrayList<>();
                
                char nextCharacter = expression.charAt(1);
                int i = 1;
                int nest = 1;
                int startOfCurrentOption = 1;
                while (nextCharacter != ')' || 1 < nest) {
                    if (nextCharacter == '(') {
                        nest++;
                    } else if (nextCharacter == ')') {
                        nest--;
                    } else if (nextCharacter == '|' && nest == 1) {
                        // Found a pipe '|', indicating the end of one of the options.
                        options.add(parse(expression.substring(startOfCurrentOption, i)));
                        startOfCurrentOption = i + 1;
                    }
                    nextCharacter = expression.charAt(i + 1);
                    i++;
                }
                // Found the closing bracket ')'.
                // Parse the last option.
                options.add(parse(expression.substring(startOfCurrentOption, i)));
                RoomMapExpression remaining = parse(expression.substring(i + 1));
                result = new Concatenation(new Branch(options), remaining);
            } else {
                // Direction(s).
                int i = 1;
                while (i < expression.length() && Character.isUpperCase(expression.charAt(i))) {
                    i++;
                }
                RoomMapExpression directions = Direction.parse(expression.substring(0, i));
                result = new Concatenation(directions, parse(expression.substring(i)));
            }
        }
        
        return result;
    }
}
