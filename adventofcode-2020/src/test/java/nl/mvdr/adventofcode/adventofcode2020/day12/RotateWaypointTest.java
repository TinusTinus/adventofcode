package nl.mvdr.adventofcode.adventofcode2020.day12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;
import nl.mvdr.adventofcode.point.TurnDirection;

/**
 * Unit test cases for {@link RotateWaypoint}.
 *
 * @author Martijn van de Rijdt
 */
public class RotateWaypointTest {
    /** Test case for {@link RotateWaypoint#execute(Ship)}, based on an example from the puzzle text. */
    @Test
    public void testExecute() {
        RotateWaypoint action = new RotateWaypoint(TurnDirection.RIGHT, 90);
        Ship ship = new Ship(new Point(170, -38), Direction.RIGHT, new Point(10, -4));
        
        Ship result = action.execute(ship);
        
        Assertions.assertEquals(new Point(170, -38), result.location());
        Assertions.assertEquals(Direction.RIGHT, result.direction());
        Assertions.assertEquals(new Point(4, 10), result.waypoint());
    }
}
