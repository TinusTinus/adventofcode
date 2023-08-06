package nl.mvdr.adventofcode.adventofcode2022.day17;

import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    MINUS,
    /**
     * A rock in the following shape:
     * 
     * <pre>
     * .#.
     * ###
     * .#.
     * </pre>
     */
    PLUS,
    /**
     * A rock in the following shape:
     * 
     * <pre>
     * ..#
     * ..#
     * ###
     * </pre>
     */
    J,
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
    I,
    /**
     * A rock in the following shape:
     * 
     * <pre>
     * ##
     * ##
     * </pre>
     */
    O;
    
    /**
     * Creates a queue filled with one of each shape, in the order they will start appearing.
     * 
     * @return queue of shapes
     */
    static Queue<Shape> initialShapeQueue() {
        return Stream.of(values())
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
