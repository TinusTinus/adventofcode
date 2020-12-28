package nl.mvdr.adventofcode.point;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit test cases for {@link Point4D}.
 *
 * @author Martijn van de Rijdt
 */
public class Point4DTest {
    /** Test method for {@link Point4D#neighbours()}.*/
    @Test
    public void testNeighbours() {
        Point4D point = new Point4D(0, 0, 0, 0);
        
        Set<Point4D> neighbours = point.neighbours();
        
        // A point is not its own neighbour.
        Assertions.assertFalse(neighbours.contains(point));
        // There must be 80 points...
        Assertions.assertEquals(80, neighbours.size(), "Neighbours: " + neighbours);
        // ... and any of their coordinates must differ at most one from the original point.
        neighbours.stream()
                .mapToInt(Point4D::x)
                .map(x -> Math.abs(x - point.x()))
                .forEach(difference -> Assertions.assertTrue(difference <= 1));
        neighbours.stream()
                .mapToInt(Point4D::y)
                .map(y -> Math.abs(y - point.y()))
                .forEach(difference -> Assertions.assertTrue(difference <= 1));
        neighbours.stream()
                .mapToInt(Point4D::z)
                .map(z -> Math.abs(z - point.z()))
                .forEach(difference -> Assertions.assertTrue(difference <= 1));
        neighbours.stream()
                .mapToInt(Point4D::w)
                .map(w -> Math.abs(w - point.w()))
                .forEach(difference -> Assertions.assertTrue(difference <= 1));

    }
}
