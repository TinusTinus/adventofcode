package nl.mvdr.adventofcode.adventofcode2018.day17;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

import javax.annotation.processing.Generated;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.point.Point;

/**
 * A two-dimensional vertical slice of the ground.
 *
 * @author Martijn van de Rijdt
 */
class VerticalSlice {
    /** Logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(VerticalSlice.class);
    
    /** The square meter where water originates. */
    private final Point spring;
    /** The square meters where the ground is clay. */
    private final Set<Point> clay;
    /** The square meters where water has settled. */
    private final Set<Point> water;
    /** The square meters where water has passed through. */
    private final Set<Point> wetSand;
    
    /** Minimum x coordinate containing anything other than sand. */
    private final int minimumX;
    /** Maximum x coordinate containing anything other than sand. */
    private final int maximumX;
    /** Minimum y coordinate from the scan input (that is, the clay). */
    private final int minimumY;
    /** Maximum y coordinate from the scan input (that is, the clay). */
    private final int maximumY;

    /**
     * Constructor.
     * 
     * @param spring the quare meter where water originates
     * @param clay the square meters where the ground is clay
     * @param water the square meters where water has settled
     * @param wetSand the square meters where water has passed through
     */
    private VerticalSlice(Point spring, Set<Point> clay, Set<Point> water, Set<Point> wetSand) {
        super();
        this.spring = spring;
        this.clay = clay;
        this.water = water;
        this.wetSand = wetSand;
        
        // Compute and cache minimum and maximum coordinates
        this.minimumY = clay.stream()
                .mapToInt(Point::y)
                .min()
                .getAsInt();
        
        this.maximumY = clay.stream()
                .mapToInt(Point::y)
                .max()
                .getAsInt();
        
        Set<Point> points = new HashSet<>();
        points.add(spring);
        points.addAll(clay);
        points.addAll(water);
        points.addAll(wetSand);
        this.minimumX = points.stream()
                .mapToInt(Point::x)
                .min()
                .getAsInt();
        this.maximumX = points.stream()
                .mapToInt(Point::x)
                .max()
                .getAsInt();
    }

    /**
     * Constructor.
     * 
     * @param spring the square meter where water originates
     * @param clay the square meters where the ground is clay
     */
    VerticalSlice(Point spring, Set<Point> clay) {
        this(spring, clay, Set.of(), Set.of());
    }
    
    /** @return an updated vertical slice, equal to this slice with an additional layer of water settled, if possible */
    private VerticalSlice tick() {
        Set<Point> newWater = new HashSet<>(water);
        Set<Point> newWetSand = new HashSet<>(wetSand);
        
        Queue<Point> tricklingWater = new LinkedList<>();
        tricklingWater.add(spring);
        
        while (!tricklingWater.isEmpty()) {
            Point tricklingWaterPoint = tricklingWater.poll();
            
            Point below = tricklingWaterPoint.belowNeighbour();
            if (maximumY < below.y()) {
                // Ignore. No need to trickle down further.
            } else if (clay.contains(below) || newWater.contains(below)) {
                // The tile below is clay or water. Unable to trickle down.
                
                // Find out whether water can continue to trickle down on the left and/or right.
                
                Set<Point> visited = new HashSet<>();
                visited.add(tricklingWaterPoint);
                
                // Search to the left.
                Point left = tricklingWaterPoint.leftNeighbour();
                while(!clay.contains(left) && (clay.contains(left.belowNeighbour()) || newWater.contains(left.belowNeighbour()))) {
                    visited.add(left);
                    left = left.leftNeighbour();
                }
                if (!clay.contains(left)) {
                    visited.add(left);
                }
                
                // Search to the right.
                Point right = tricklingWaterPoint.rightNeighbour();
                while(!clay.contains(right) && (clay.contains(right.belowNeighbour()) || newWater.contains(right.belowNeighbour()))) {
                    visited.add(right);
                    right = right.rightNeighbour();
                }
                if (!clay.contains(right)) {
                    visited.add(right);
                }
                
                boolean settle = true;
                if (!(clay.contains(left.belowNeighbour()) || newWater.contains(left.belowNeighbour()))) {
                    settle = false;
                    tricklingWater.add(left);
                }
                if (!(clay.contains(right.belowNeighbour()) || newWater.contains(right.belowNeighbour()))) {
                    settle = false;
                    tricklingWater.add(right);
                }
                
                if (settle) {
                    // The water settles here.
                    newWater.addAll(visited);
                } else {
                    // The water continues trickling on the left and/or right.
                    newWetSand.addAll(visited);
                }
                
            } else {
                // The tile below is sand.
                newWetSand.add(below);
                tricklingWater.add(below);
            }
        }
        
        return new VerticalSlice(spring, clay, newWater, newWetSand);
    }
    
    /** @return this slice after water has settled wherever possible */
    VerticalSlice tickUntilDone() {
        VerticalSlice previous = this;
        VerticalSlice current = this.tick();
        while (!previous.equals(current)) {
            previous = current;
            current = current.tick();
            LOGGER.debug("Current {}", current);
        }
        return current;
    }
    
    /** @return number of tiles where water has settled */
    int settledWater() {
        return water.size();
    }
    
    /** @return number of tiles reached by water, that is: the number of tiles where water is at rest + the number of tiles where water has passed through */
    int reachedByWater() {
        // Eliminate any duplicate tiles
        Set<Point> reached = new HashSet<>();
        reached.addAll(water);
        reached.addAll(wetSand);
        
        // To prevent counting forever, ignore tiles with a y coordinate smaller than the smallest y coordinate in your scan data or larger than the largest one.
        reached.removeIf(point -> point.y() < minimumY);
        reached.removeIf(point -> maximumY < point.y());
        
        return reached.size();
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("vertical slice:\n");
        for (int y = minimumY; y != maximumY + 1; y++) {
            for (int x = minimumX; x != maximumX + 1; x++) {
                Point point = new Point(x, y);
                
                char c;
                if (spring.equals(point)) {
                    c = '+';
                } else if (clay.contains(point)) {
                    c = '#';
                } else if (water.contains(point)) {
                    c = '~';
                } else if (wetSand.contains(point)) {
                    c = '|';
                } else {
                    c = '.';
                }
                builder.append(c);
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    @Generated("Eclipse")
    @Override
    public int hashCode() {
        return Objects.hash(clay, spring, water, wetSand);
    }

    @Generated("Eclipse")
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        VerticalSlice other = (VerticalSlice) obj;
        return Objects.equals(clay, other.clay) && Objects.equals(spring, other.spring)
                && Objects.equals(water, other.water) && Objects.equals(wetSand, other.wetSand);
    }
}
