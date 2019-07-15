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
    
    /** Test case for {@link RoomMapExpression#parse(String)}, based on an example from the puzzle. */
    @Test
    public void testParseExample0() {
        String input = "^WNE$";
        
        RoomMapExpression expression = RoomMapExpression.parse(input);
        
        Assertions.assertEquals(input, expression.toString());
    }
    
    /** Test case for {@link RoomMapExpression#parse(String)}, based on an example from the puzzle. */
    @Test
    public void testParseExample1() {
        String input = "^ENWWW(NEEE|SSE(EE|N))$";
        
        RoomMapExpression expression = RoomMapExpression.parse(input);
        
        Assertions.assertEquals(input, expression.toString());
    }
    
    /** Test case for {@link RoomMapExpression#parse(String)}, based on an example from the puzzle. */
    @Test
    public void testParseExample2() {
        String input = "^ENNWSWW(NEWS|)SSSEEN(WNSE|)EE(SWEN|)NNN$";
        
        RoomMapExpression expression = RoomMapExpression.parse(input);
        
        Assertions.assertEquals(input, expression.toString());
    }

    /** Test case for {@link RoomMapExpression#parse(String)}, based on an example from the puzzle. */
    @Test
    public void testParseExample3() {
        String input = "^ESSWWN(E|NNENN(EESS(WNSE|)SSS|WWWSSSSE(SW|NNNE)))$";
        
        RoomMapExpression expression = RoomMapExpression.parse(input);
        
        Assertions.assertEquals(input, expression.toString());
    }

    /** Test case for {@link RoomMapExpression#parse(String)}, based on an example from the puzzle. */
    @Test
    public void testParseExample4() {
        String input = "^WSSEESWWWNW(S|NENNEEEENN(ESSSSW(NWSW|SSEN)|WSWWN(E|WWS(E|SS))))$";
        
        RoomMapExpression expression = RoomMapExpression.parse(input);
        
        Assertions.assertEquals(input, expression.toString());
    }
    
    /** Test case for {@link RoomMapExpression#parse(String)}. */
    @Test
    public void testParseBranchWithMultiplePipes() {
        String input = "^(N|S|E)$";
        
        RoomMapExpression expression = RoomMapExpression.parse(input);
        
        Assertions.assertEquals(input, expression.toString());
    }
}
