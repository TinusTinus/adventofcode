package nl.mvdr.adventofcode.adventofcode2022.day17;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * Representation of the chamber.
 * 
 * Note that this class is mutable.
 * Solving the puzzle with an immutable class would involve a lot of list copying.
 *
 * @author Martijn van de Rijdt
 */
class Chamber {
    
    /** Width of the chamber. */
    private static final int WIDTH = 7;
    
    /** Directions of the gusts, in order. */
    private Queue<Direction> jetStream;
    /** Falling shapes, in order. */
    private Queue<Shape> shapes;
    /** The current tower. */
    private Set<Point> tower;
    /** The number of settled rocks making up the tower. */
    private int settledRockCount;
    /** The current falling rock. */
    private Set<Point> fallingRock;
    
    /**
     * Creates a new chamber, in its initial state.
     * 
     * @param lines puzzle input, containing the representation of the jet stream
     * @return initial state of the chamber
     */
    static Chamber initialize(Stream<String> lines) {
        Queue<Direction> jetStream = lines.findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No input provided."))
                .chars()
                .mapToObj(c -> "" + (char)c)
                .map(Direction::parse)
                .collect(Collectors.toCollection(LinkedList::new));
        return new Chamber(jetStream);
    }
    
    /**
     * Constructor.
     * 
     * @param jetStream initial jet stream
     */
    private Chamber(Queue<Direction> jetStream) {
        super();
        this.jetStream = jetStream;
        this.shapes = Shape.initialShapeQueue();
        this.tower = new HashSet<>();
        this.settledRockCount = 0;
        spawnRock();
    }
    
    /**
     * Spawns a new rock.
     */
    private void spawnRock() {
        // Get the first shape from the queue.
        Shape shape = shapes.remove();
        // Re-insert, at the end of the queue.
        shapes.add(shape);
        
        fallingRock = new HashSet<>(); // TODO create based on the shape and current height
    }
    
    /**
     * Performs a single tick.
     */
    void tick() {
        throw new UnsupportedOperationException(); // TODO actually implement!
    }
    
    /**
     * @return height of the tower
     */
    int height() {
        return tower.stream()
                .mapToInt(Point::y)
                .max()
                .orElse(0);
    }
    
    /**
     * @return the number of settled rocks making up the tower
     */
    int getSettledRockCount() {
        return settledRockCount;
    }

}
