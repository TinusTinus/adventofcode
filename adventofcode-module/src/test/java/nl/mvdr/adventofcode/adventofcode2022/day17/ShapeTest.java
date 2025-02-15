package nl.mvdr.adventofcode.adventofcode2022.day17;

import java.util.Queue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit test cases for {@link Shape}.
 *
 * @author Martijn van de Rijdt
 */
public class ShapeTest {
    /**
     * Test case for {@link Shape#initialShapeQueue()}
     */
    @Test
    public void testQueue() {
        Queue<Shape> queue = Shape.initialShapeQueue();
        //
        Assertions.assertEquals(Shape.MINUS, queue.remove());
        Assertions.assertEquals(Shape.PLUS, queue.remove());
        Assertions.assertEquals(Shape.J, queue.remove());
        Assertions.assertEquals(Shape.I, queue.remove());
        Assertions.assertEquals(Shape.O, queue.remove());
        Assertions.assertTrue(queue.isEmpty());
    }
}
