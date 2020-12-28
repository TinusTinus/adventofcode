package nl.mvdr.adventofcode.point;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit test cases for {@link Point3D}.
 *
 * @author Martijn van de Rijdt
 */
public class Point3DTest {
    /** Test method for {@link Point3D#neighbours()}.*/
    @Test
    public void testNeighbours() {
        Point3D point = new Point3D(0, 0, 0);
        
        Set<Point3D> neighbours = point.neighbours();
        
        // A point is not its own neighbour.
        Assertions.assertFalse(neighbours.contains(point));
        // There must be 26 points...
        Assertions.assertEquals(26, neighbours.size(), "Neighbours: " + neighbours);
        // ... and any of their coordinates must differ at most one from the original point.
        neighbours.stream()
                .mapToInt(Point3D::x)
                .map(x -> Math.abs(x - point.x()))
                .forEach(difference -> Assertions.assertTrue(difference <= 1));
        neighbours.stream()
                .mapToInt(Point3D::y)
                .map(y -> Math.abs(y - point.y()))
                .forEach(difference -> Assertions.assertTrue(difference <= 1));
        neighbours.stream()
                .mapToInt(Point3D::z)
                .map(z -> Math.abs(z - point.z()))
                .forEach(difference -> Assertions.assertTrue(difference <= 1));
    }
}
