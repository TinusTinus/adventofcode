package nl.mvdr.adventofcode.adventofcode2022.day08;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * Unit tests for {@link Forest}.
 *
 * @author Martijn van de Rijdt
 */
public class ForestTest {
    
    private Forest forest;
    
    /**
     * @return the forest from the puzzle's example
     */
    @BeforeEach
    public void createExampleForest() {
        Stream<String> lines = Stream.of(
                "30373",
                "25512",
                "65332",
                "33549",
                "35390"
            );
        this.forest = Forest.parse(lines);
    }
    
    /**
     * Tests trees at the edge of the forest.
     */
    @Test
    public void testTopEdge() {
        IntStream.range(0, 5)
                .mapToObj(x -> new Point(x, 0))
                .peek(location -> Assertions.assertTrue(forest.isVisibleFrom(location, Direction.UP)))
                .forEach(location -> Assertions.assertTrue(forest.isVisible(location)));
    }
    
    /**
     * Tests trees at the edge of the forest.
     */
    @Test
    public void testBottomEdge() {
        IntStream.range(0, 5)
                .mapToObj(x -> new Point(x, 4))
                .peek(location -> Assertions.assertTrue(forest.isVisibleFrom(location, Direction.DOWN)))
                .forEach(location -> Assertions.assertTrue(forest.isVisible(location)));
    }
    
    /**
     * Tests trees at the edge of the forest.
     */
    @Test
    public void testLeftEdge() {
        IntStream.range(0, 5)
                .mapToObj(y -> new Point(0, y))
                .peek(location -> Assertions.assertTrue(forest.isVisibleFrom(location, Direction.LEFT)))
                .forEach(location -> Assertions.assertTrue(forest.isVisible(location)));
    }
    
    /**
     * Tests trees at the edge of the forest.
     */
    @Test
    public void testRightEdge() {
        IntStream.range(0, 5)
                .mapToObj(y -> new Point(4, y))
                .peek(location -> Assertions.assertTrue(forest.isVisibleFrom(location, Direction.RIGHT)))
                .forEach(location -> Assertions.assertTrue(forest.isVisible(location)));
    }
    
    /**
     * Test case for {@link Forest#isVisibleFrom(Point, Direction)} and {@link Forest#isVisible(Point)}.
     * 
     * The top-left 5 is visible from the left and top.
     * (It isn't visible from the right or bottom since other trees of height 5 are in the way.)
     */
    @Test
    public void testTopLeft5() {
        Point location = new Point(1, 1);
        
        Assertions.assertTrue(forest.isVisibleFrom(location, Direction.LEFT));
        Assertions.assertTrue(forest.isVisibleFrom(location, Direction.UP));
        Assertions.assertFalse(forest.isVisibleFrom(location, Direction.DOWN));
        Assertions.assertFalse(forest.isVisibleFrom(location, Direction.RIGHT));
        Assertions.assertTrue(forest.isVisible(location));
    }
    
    /**
     * Test case for {@link Forest#isVisibleFrom(Point, Direction)} and {@link Forest#isVisible(Point)}.
     * 
     * The top-middle 5 is visible from the top and right.
     */
    @Test
    public void testTopMiddle5() {
        Point location = new Point(2, 1);
        
        Assertions.assertFalse(forest.isVisibleFrom(location, Direction.LEFT));
        Assertions.assertTrue(forest.isVisibleFrom(location, Direction.UP));
        Assertions.assertFalse(forest.isVisibleFrom(location, Direction.DOWN));
        Assertions.assertTrue(forest.isVisibleFrom(location, Direction.RIGHT));
        Assertions.assertTrue(forest.isVisible(location));
    }
    
    /**
     * Test case for {@link Forest#isVisibleFrom(Point, Direction)} and {@link Forest#isVisible(Point)}.
     * 
     * The top-right 1 is not visible from any direction;
     * for it to be visible, there would need to only be trees of height 0 between it and an edge.
     */
    @Test
    public void testTopRight1() {
        Point location = new Point(3, 1);
        Assertions.assertFalse(forest.isVisibleFrom(location, Direction.LEFT));
        Assertions.assertFalse(forest.isVisibleFrom(location, Direction.UP));
        Assertions.assertFalse(forest.isVisibleFrom(location, Direction.DOWN));
        Assertions.assertFalse(forest.isVisibleFrom(location, Direction.RIGHT));
        Assertions.assertFalse(forest.isVisible(location));
    }
    
    /**
     * Test case for {@link Forest#isVisibleFrom(Point, Direction)} and {@link Forest#isVisible(Point)}.
     * 
     * The left-middle 5 is visible, but only from the right.
     */
    @Test
    public void testLeftMiddle5() {
        Point location = new Point(1, 2);
        Assertions.assertFalse(forest.isVisibleFrom(location, Direction.LEFT));
        Assertions.assertFalse(forest.isVisibleFrom(location, Direction.UP));
        Assertions.assertFalse(forest.isVisibleFrom(location, Direction.DOWN));
        Assertions.assertTrue(forest.isVisibleFrom(location, Direction.RIGHT));
        Assertions.assertTrue(forest.isVisible(location));
    }
    
    /**
     * Test case for {@link Forest#isVisibleFrom(Point, Direction)} and {@link Forest#isVisible(Point)}.
     * 
     * The center 3 is not visible from any direction;
     * for it to be visible, there would need to be only trees of at most height 2 between it and an edge.
     */
    @Test
    public void testMiddle3() {
        Point location = new Point(2, 2);
        Assertions.assertFalse(forest.isVisibleFrom(location, Direction.LEFT));
        Assertions.assertFalse(forest.isVisibleFrom(location, Direction.UP));
        Assertions.assertFalse(forest.isVisibleFrom(location, Direction.DOWN));
        Assertions.assertFalse(forest.isVisibleFrom(location, Direction.RIGHT));
        Assertions.assertFalse(forest.isVisible(location));
    }
    
    /**
     * Test case for {@link Forest#isVisibleFrom(Point, Direction)} and {@link Forest#isVisible(Point)}.
     * 
     * The right-middle 3 is visible from the right.
     */
    @Test
    public void testRightMiddle3() {
        Point location = new Point(3, 2);
        Assertions.assertFalse(forest.isVisibleFrom(location, Direction.LEFT));
        Assertions.assertFalse(forest.isVisibleFrom(location, Direction.UP));
        Assertions.assertFalse(forest.isVisibleFrom(location, Direction.DOWN));
        Assertions.assertTrue(forest.isVisibleFrom(location, Direction.RIGHT));
        Assertions.assertTrue(forest.isVisible(location));
    }

    /**
     * Test case for {@link Forest#isVisibleFrom(Point, Direction)} and {@link Forest#isVisible(Point)}.
     * 
     * In the bottom row, the middle 5 is visible, but the 3 and 4 are not.
     */
    @Test
    public void testBottomMiddleRow() {
        Assertions.assertFalse(forest.isVisible(new Point(1, 3)));
        Assertions.assertTrue(forest.isVisible(new Point(2, 3)));
        Assertions.assertFalse(forest.isVisible(new Point(3, 3)));
    }
}
