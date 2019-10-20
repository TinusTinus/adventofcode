package nl.mvdr.adventofcode.point;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test cases for {@link Direction}.
 *
 * @author Martijn van de Rijdt
 */
public class DirectionTest {
    /** Test case for {@link Direction#reverse()}. */
    @Test
    public void testReverseUp() {
        Direction input = Direction.UP;
        
        Direction result = input.reverse();
        
        Assertions.assertEquals(Direction.DOWN, result);
    }
    
    /** Test case for {@link Direction#reverse()}. */
    @Test
    public void testReverseDown() {
        Direction input = Direction.DOWN;
        
        Direction result = input.reverse();
        
        Assertions.assertEquals(Direction.UP, result);
    }
    
    /** Test case for {@link Direction#reverse()}. */
    @Test
    public void testReverseLeft() {
        Direction input = Direction.LEFT;
        
        Direction result = input.reverse();
        
        Assertions.assertEquals(Direction.RIGHT, result);
    }
    
    /** Test case for {@link Direction#reverse()}. */
    @Test
    public void testReverseRight() {
        Direction input = Direction.RIGHT;
        
        Direction result = input.reverse();
        
        Assertions.assertEquals(Direction.LEFT, result);
    }
    
    /** Test case for {@link Direction#turnClockwise()}. */
    @Test
    public void testTurnClockwiseUp() {
        Direction input = Direction.UP;
        
        Direction result = input.turnClockwise();
        
        Assertions.assertEquals(Direction.RIGHT, result);
    }
    
    /** Test case for {@link Direction#turnClockwise()}. */
    @Test
    public void testTurnClockwiseLeft() {
        Direction input = Direction.LEFT;
        
        Direction result = input.turnClockwise();
        
        Assertions.assertEquals(Direction.UP, result);
    }
    
    /** Test case for {@link Direction#turnClockwise()}. */
    @Test
    public void testTurnClockwiseDown() {
        Direction input = Direction.DOWN;
        
        Direction result = input.turnClockwise();
        
        Assertions.assertEquals(Direction.LEFT, result);
    }
    
    /** Test case for {@link Direction#turnClockwise()}. */
    @Test
    public void testTurnClockwiseRight() {
        Direction input = Direction.RIGHT;
        
        Direction result = input.turnClockwise();
        
        Assertions.assertEquals(Direction.DOWN, result);
    }
    
    /** Test case for {@link Direction#turnCounterClockwise()}. */
    @Test
    public void testTurnCounterClockwiseUp() {
        Direction input = Direction.UP;
        
        Direction result = input.turnCounterClockwise();
        
        Assertions.assertEquals(Direction.LEFT, result);
    }
    
    /** Test case for {@link Direction#turnCounterClockwise()}. */
    @Test
    public void testTurnCounterClockwiseLeft() {
        Direction input = Direction.LEFT;
        
        Direction result = input.turnCounterClockwise();
        
        Assertions.assertEquals(Direction.DOWN, result);
    }
    
    /** Test case for {@link Direction#turnCounterClockwise()}. */
    @Test
    public void testTurnCounterClockwiseDown() {
        Direction input = Direction.DOWN;
        
        Direction result = input.turnCounterClockwise();
        
        Assertions.assertEquals(Direction.RIGHT, result);
    }
    
    /** Test case for {@link Direction#turnCounterClockwise()}. */
    @Test
    public void testTurnCounterClockwiseRight() {
        Direction input = Direction.RIGHT;
        
        Direction result = input.turnCounterClockwise();
        
        Assertions.assertEquals(Direction.UP, result);
    }
}
