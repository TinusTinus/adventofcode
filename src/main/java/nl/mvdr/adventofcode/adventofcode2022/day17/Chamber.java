package nl.mvdr.adventofcode.adventofcode2022.day17;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Chamber.class);
    /** Width of the chamber. */
    private static final int WIDTH = 7;
    
    /** Initial directions of the gusts, in order. */
    private final Queue<Direction> initialJetStream;
    /** Directions of the gusts, in order. */
    private Queue<Direction> jetStream;
    /** Falling shapes, in order. */
    private Queue<Shape> shapes;
    /** The current tower. */
    private Set<Point> tower;
    /** The number of settled rocks making up the tower. */
    private int settledRockCount;
    /** Height of each column (indexed by the x coordinate). */
    private int[] heights;
    /** The current falling rock. */
    private Set<Point> fallingRock;
    
    /**
     * Creates a new chamber, in its initial state.
     * 
     * @param jetStreamString string representation of the directions 
     * @return initial state of the chamber
     */
    static Chamber initialize(String jetStreamString) {
        Queue<Direction> jetStream = jetStreamString
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
        this.initialJetStream = new LinkedList<>(jetStream);
        this.jetStream = jetStream;
        this.shapes = Shape.initialShapeQueue();
        this.tower = new HashSet<>();
        this.settledRockCount = 0;
        this.heights = new int[WIDTH];
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
        
        fallingRock = shape.spawn(height());
    }
    
    /**
     * Continues the simulation until the specified number of rocks has settled.
     * 
     * @param rocksDropped number of rocks
     */
    void simulateUntil(int rocksDropped) {
        simulateWhile(() -> settledRockCount < rocksDropped);
    }
    
    /**
     * Continues the simulation until a repeated pattern is detected.
     * 
     * The pattern repeats as soon as the chamber has been topped off,
     * and the jet stream is in its initial state.
     */
    void simulateUntilRepeats() {
        simulateWhile(() -> !startsRepeatingPattern());
    }

    /**
     * @return whether the current state is the start of a repeating pattern
     */
    private boolean startsRepeatingPattern() {
        return isToppedOff() && jetStream.equals(initialJetStream);
    }
    
    /**
     * Checks whether the chamber is topped off, that is:
     * whether the top row is entirely blocked off by settled rock.
     * 
     * @return whether the chamber is topped off
     */
    private boolean isToppedOff() {
        var height = height();
        var result = IntStream.range(0, WIDTH)
            .mapToObj(x -> new Point(x, height - 1))
            .allMatch(tower::contains);
        
        // Clean up everything below the topped off layer; we don't need it for anything anymore.
        if (result && tower.removeIf(point -> point.y() < height - 1)) {
            LOGGER.info("Lines below {} cleared. Rocks settled: {}", Integer.valueOf(height), Integer.valueOf(settledRockCount)); // TODO debug
        }
        return result;
    }
    
    /**
     * Continues the simulation until the given condition is true.
     * 
     * @param rocksDropped number of rocks
     */
    private void simulateWhile(BooleanSupplier condition) {
        LOGGER.debug("{}", this);
        while (condition.getAsBoolean()) {
            tick();
        }
    }
    
    /**
     * Performs a single tick.
     */
    private void tick() {
        push();
        fall();
    }

    /**
     * Lets the falling block be pushed one unit by a jet of hot gas.
     */
    private void push() {
        // Get the first direction from the queue.
        var direction = jetStream.remove();
        // Re-instert, at the end of the queue.
        jetStream.add(direction);
        
        var newFallingRock = fallingRock.stream()
                .map(point -> direction.move(point))
                .collect(Collectors.toSet());
        if (canMoveTo(newFallingRock)) {
            fallingRock = newFallingRock;
        } // Otherwise: just stay
    }
    
    /**
     * Lets the falling block fall one unit down.
     */
    private void fall() {
        var newFallingRock = fallingRock.stream()
                .map(point -> new Point(point.x(), point.y() - 1))  
                .collect(Collectors.toSet());
        
        if (canMoveTo(newFallingRock)) {
            // Move down
            fallingRock = newFallingRock;
        } else {
            // Settle
            tower.addAll(fallingRock);
            settledRockCount++;
            for (Point point : fallingRock) {
                heights[point.x()] = Math.max(heights[point.x()], point.y() + 1);
            }
            
            // Immediately a new rock starts falling
            spawnRock();
        }
    }
    
    /**
     * Checks whether the falling rock can move to the given new location.
     * 
     * That is, whether the new location is within bounds and not occupied by a settled rock.
     * 
     * @param newLocation new location
     * @return whether the new location can be occupied by the falling rock
     */
    private boolean canMoveTo(Set<Point> newLocation) {
        return newLocation.stream().allMatch(this::canMoveTo);
    }
    
    /**
     * Checks whether a part of the falling rock can move to the given new location.
     * 
     * That is, whether the new location is within bounds and not occupied by a settled rock.
     * 
     * @param newLocation new location
     * @return whether the new location can be occupied by the falling rock
     */
    private boolean canMoveTo(Point newLocation) {
        return 0 <= newLocation.x() 
                && newLocation.x() < WIDTH 
                && 0 <= newLocation.y()
                // First check the height (for performance)
                && (heights[newLocation.x()] < newLocation.y() || !tower.contains(newLocation));
    }
    
    /**
     * @return height of the tower
     */
    int height() {
        return IntStream.of(heights).max().orElseThrow();
    }
    
    /**
     * @return the number of settled rocks making up the tower
     */
    int getSettledRockCount() {
        return settledRockCount;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Chamber:\n");
        for (int y = fallingRock.stream().mapToInt(Point::y).max().orElseThrow(); y != -1; y--) {
            builder.append("|");
            for (int x = 0; x != WIDTH; x++) {
                Point point = new Point(x, y);
                if (fallingRock.contains(point)) {
                    builder.append("@");
                } else if (tower.contains(point)) {
                    builder.append("#");
                } else {
                    builder.append(".");
                }
            }
            builder.append("|\n");
        }
        builder.append("+-------+");
        return builder.toString();
    }
}
