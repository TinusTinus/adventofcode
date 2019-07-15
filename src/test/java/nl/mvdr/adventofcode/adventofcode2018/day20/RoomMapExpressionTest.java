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
    public void testParse() {
        String input = "^WSSEESWWWNW(S|NENNEEEENN(ESSSSW(NWSW|SSEN)|WSWWN(E|WWS(E|SS))))$";
        
        RoomMapExpression expression = RoomMapExpression.parse(input);
        
        Assertions.assertEquals("WSSEESWWWNW(S|NENNEEEENN(ESSSSW(NWSW|SSEN)|WSWWN(E|WWS(E|SS))))", expression.toString());
    }
}
