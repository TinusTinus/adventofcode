package nl.mvdr.adventofcode.adventofcode2018.day06;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link Point}.
 *
 * @author Martijn van de Rijdt
 */
public class PointTest {
    
    /** Test case for {@link Point#manhattanDistance(Point)}. */
    @Test
    public void testManhattanDistance() {
        Point bottomLeft = new Point(0, 0);
        Point topRight = new Point(6, 6);
        
        int manhattanDistance = bottomLeft.manhattanDistance(topRight);
        
        Assertions.assertEquals(12, manhattanDistance);
    }
    
    /** Test case for {@link Point#manhattanDistance(Point)}. */
    @Test
    public void testManhattanDistanceOtherWayAround() {
        Point bottomLeft = new Point(0, 0);
        Point topRight = new Point(6, 6);
        
        int manhattanDistance = topRight.manhattanDistance(bottomLeft);
        
        Assertions.assertEquals(12, manhattanDistance);
    }
    
    /** Test case for {@link Point#manhattanDistance(Point)}. */
    @Test
    public void testManhattanDistanceSelf() {
        Point point = new Point(1, 5);
        
        int manhattanDistance = point.manhattanDistance(point);
        
        Assertions.assertEquals(0, manhattanDistance);
    }
}
