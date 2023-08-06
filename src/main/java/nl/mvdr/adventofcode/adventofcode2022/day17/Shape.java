package nl.mvdr.adventofcode.adventofcode2022.day17;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.point.Point;

/**
 * Types of falling rocks.
 *
 * @author Martijn van de Rijdt
 */
enum Shape {
    /**
     * A rock in the following shape:
     * 
     * <pre>
     * ####
     * </pre>
     */
    MINUS(Set.of(new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(3, 0))),
    /**
     * A rock in the following shape:
     * 
     * <pre>
     * .#.
     * ###
     * .#.
     * </pre>
     */
    PLUS(Set.of(new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(1, 2))),
    /**
     * A rock in the following shape:
     * 
     * <pre>
     * ..#
     * ..#
     * ###
     * </pre>
     */
    J(Set.of(new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(2, 1), new Point(2, 2))),
    /**
     * A rock in the following shape:
     * 
     * <pre>
     * #
     * #
     * #
     * #
     * </pre>
     */
    I(Set.of(new Point(0, 0), new Point(0, 1), new Point(0, 2), new Point(0, 3))),
    /**
     * A rock in the following shape:
     * 
     * <pre>
     * ##
     * ##
     * </pre>
     */
    O(Set.of(new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1)));
    
    private final Set<Point> rock;
    
    /**
     * Constructor.
     * 
     * @param rock the different points occupied by this shape, if a rock were to spawn at (0, 0) 
     */
    private Shape(Set<Point> rock) {
        this.rock = rock;
    }
    
    /**
     * Creates a queue filled with one of each shape, in the order they will start appearing.
     * 
     * @return queue of shapes
     */
    static Queue<Shape> initialShapeQueue() {
        return Stream.of(values())
                .collect(Collectors.toCollection(LinkedList::new));
    }
    
    /**
     * Spawns a new rock.
     * 
     * Each rock appears so that its left edge is two units away from the left wall
     * and its bottom edge is three units above the highest rock in the room (or the
     * floor, if there isn't one).
     * 
     * @param height current height of the tower
     * @return new rock
     */
    Set<Point> spawn(int height) {
        var offset = new Point(2, height + 3);
        return rock.stream()
                .map(point -> point.translate(offset))
                .collect(Collectors.toSet());
    }
}
