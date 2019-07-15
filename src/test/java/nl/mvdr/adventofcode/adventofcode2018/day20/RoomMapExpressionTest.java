package nl.mvdr.adventofcode.adventofcode2018.day20;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link RoomMapExpression}.
 *
 * @author Martijn van de Rijdt
 */
public class RoomMapExpressionTest {
    /** Test case for {@link RoomMapExpression#parse(String)}. */
    @Test
    public void testParseEmptyExpression() {
        String input = "^$";
        
        RoomMapExpression expression = CompleteExpression.parse(input);
        
        Assertions.assertEquals(input, expression.toString());
    }
    
    /** Test case for {@link RoomMapExpression#parse(String)}. */
    @Test
    public void testParse() {
        String input = "^WSSEESWWWNW(S|NENNEEEENN(ESSSSW(NWSW|SSEN)|WSWWN(E|WWS(E|SS))))$";
        
        RoomMapExpression expression = CompleteExpression.parse(input);
        
        Assertions.assertEquals(input, expression.toString());
    }
}
