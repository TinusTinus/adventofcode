package nl.mvdr.adventofcode.adventofcode2022.day22;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * Unit test for {@link Position}.
 *
 * @author Martijn van de Rijdt
 */
public class PositionTest {
    
    /**
     * Test case for {@link Position#computePassword()}.
     * 
     * In the example, the final row is 6, the final column is 8, and the final facing is 0.
     * So, the final password is 1000 * 6 + 4 * 8 + 0: 6032.
     */
    @Test
    public void testComputePassword() {
        var position = new Position(new Point(8, 6), Direction.RIGHT);
        
        var password = position.computePassword();
        
        Assertions.assertEquals(6032, password);
    }
}
