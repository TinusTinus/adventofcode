package nl.mvdr.adventofcode.adventofcode2018.day20;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link RoomMapExpression}.
 *
 * @author Martijn van de Rijdt
 */
public class RoomMapExpressionTest {
    /** Test case for {@link RoomMapExpression#parse(String)}, with an empty expression. */
    @Test
    public void testParseEmptyExpression() {
        String input = "^$";
        
        RoomMapExpression expression = RoomMapExpression.parse(input);
        
        Assertions.assertEquals(input, expression.toString());
    }
    
    /** Test case for {@link RoomMapExpression#parse(String)}, with an expression containing only concatenation, no branches. */
    @Test
    public void testParseConcatenation() {
        String input = "^WSSEESWWWNW$";
        
        RoomMapExpression expression = RoomMapExpression.parse(input);
        
        Assertions.assertEquals(input, expression.toString());
    }

    /** Test case for {@link RoomMapExpression#parse(String)}. */
    @Test
    public void testParseSimpleBranch() {
        String input = "^(N|S)$";
        
        RoomMapExpression expression = RoomMapExpression.parse(input);
        
        Assertions.assertEquals(input, expression.toString());
    }
    
    /** Test case for {@link RoomMapExpression#parse(String)}. */
    @Test
    public void testParseComplexExpression() {
        String input = "^WSSEESWWWNW(S|NENNEEEENN(ESSSSW(NWSW|SSEN)|WSWWN(E|WWS(E|SS))))$";
        
        RoomMapExpression expression = RoomMapExpression.parse(input);
        
        Assertions.assertEquals(input, expression.toString());
    }
}
