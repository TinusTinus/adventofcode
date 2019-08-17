package nl.mvdr.adventofcode.point;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.point.Point;

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
    
    /** Test case for {@link Point#totalManhattanDistance(Set))}. */
    @Test
    public void testTotalManhattanDistanceEmptySet() {
        Point point = new Point(0, 0);
        Set<Point> points = Set.of();
        
        int manhattanDistance = point.totalManhattanDistance(points);
        
        Assertions.assertEquals(0, manhattanDistance);
    }
    
    /** Test case for {@link Point#totalManhattanDistance(Set))}. */
    @Test
    public void testTotalManhattanDistanceSingleton() {
        Point point = new Point(0, 0);
        Set<Point> points = Set.of(new Point(6, 6));
        
        int manhattanDistance = point.totalManhattanDistance(points);
        
        Assertions.assertEquals(12, manhattanDistance);
    }
    
    /** Test case for {@link Point#totalManhattanDistance(Set))}. */
    @Test
    public void testTotalManhattanDistanceSingletonOtherWayAround() {
        Point point = new Point(6, 6);
        Set<Point> points = Set.of(new Point(0, 0));
        
        int manhattanDistance = point.totalManhattanDistance(points);
        
        Assertions.assertEquals(12, manhattanDistance);
    }
    
    /** Test case for {@link Point#totalManhattanDistance(Set))}. */
    @Test
    public void testTotalManhattanDistanceExample() {
        Point point = new Point(4, 3);
        Set<Point> points = Set.of(
            new Point(1, 1), 
            new Point(1, 6), 
            new Point(8, 3), 
            new Point(3, 4), 
            new Point(5, 5), 
            new Point(8, 9)
        );
        
        int manhattanDistance = point.totalManhattanDistance(points);
        
        Assertions.assertEquals(30, manhattanDistance);
    }
    
    /** Test case for {@link Point#neighbours()}. */
    @Test
    public void testNeighbours() {
        Point point = new Point(2, 3);
        
        Set<Point> neighbours = point.neighbours();
        
        Assertions.assertEquals(4, neighbours.size());
        Assertions.assertTrue(neighbours.contains(new Point(1, 3)));
        Assertions.assertTrue(neighbours.contains(new Point(3, 3)));
        Assertions.assertTrue(neighbours.contains(new Point(2, 2)));
        Assertions.assertTrue(neighbours.contains(new Point(2, 4)));
    }
    
    /** Test case for {@link Point#parseRange(String)} */
    @Test
    public void testParseRangeVerticalLine() {
        String input = "x=438, y=595..597";
        
        Set<Point> result = Point.parseRange(input);
        
        Assertions.assertEquals(Set.of(new Point(438, 595), new Point(438, 596), new Point(438, 597)), result);
    }
    
    /** Test case for {@link Point#parseRange(String)} */
    @Test
    public void testParseRangeHorizontalLine() {
        String input = "y=1230, x=524..528";
        
        Set<Point> result = Point.parseRange(input);
        
        Assertions.assertEquals(Set.of(new Point(524, 1230), new Point(525, 1230), new Point(526, 1230), new Point(527, 1230), new Point(528, 1230)), result);
    }
}
