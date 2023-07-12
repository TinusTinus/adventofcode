package nl.mvdr.adventofcode.verticalslice;

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
 * A two-dimensional vertical slice of a location, intended to simulate the behavior of falling material.
 * 
 * @author Martijn van de Rijdt
 */
public class VerticalSlice {
    /** Logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(VerticalSlice.class);
    
    /** The tile where falling material originates. */
    private final Point spring;
    /** The tiles where there is solid material, which the falling material cannot pass through. */
    private final Set<Point> solid;
    /** The tiles where falling material has settled. */
    private final Set<Point> settled;
    /** The tiles where falling material has passed through. */
    private final Set<Point> passedThrough;
    
    /**
     * Whether the falling material is a liquid.
     * 
     * Determines whether the falling material can settle on its own or if it will continue trickling left / right. 
     */
    private final boolean liquidFallingMaterial;
    
    /** Minimum x coordinate containing anything other than empty tiles. */
    private final int minimumX;
    /** Maximum x coordinate containing anything other than empty tiles. */
    private final int maximumX;
    /** Minimum y coordinate from the scan input (that is, the solid material). */
    private final int minimumY;
    /** Maximum y coordinate from the scan input (that is, the solid material). */
    private final int maximumY;

    /**
     * Constructor.
     * 
     * @param spring the tile where falling material originates
     * @param solid the tiles containing solid material
     * @param settled the tiles where falling material has settled
     * @param passedThrough the tiles where falling material has passed through
     * @param liquidFallingMaterial whether the falling material is a liquid
     */
    private VerticalSlice(Point spring, Set<Point> solid, Set<Point> settled, Set<Point> passedThrough, boolean liquidFallingMaterial) {
        super();
        this.spring = spring;
        this.solid = solid;
        this.settled = settled;
        this.passedThrough = passedThrough;
        this.liquidFallingMaterial = liquidFallingMaterial;
        
        // Compute and cache minimum and maximum coordinates
        this.minimumY = solid.stream()
                .mapToInt(Point::y)
                .min()
                .getAsInt();
        
        this.maximumY = solid.stream()
                .mapToInt(Point::y)
                .max()
                .getAsInt();
        
        Set<Point> points = new HashSet<>();
        points.add(spring);
        points.addAll(solid);
        points.addAll(settled);
        points.addAll(passedThrough);
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
     * @param clay the tiles containing solid material
     * @param liquidFallingMaterial whether the falling material is a liquid
     */
    public VerticalSlice(Point spring, Set<Point> solid, boolean liquidFallingMaterial) {
        this(spring, solid, Set.of(), Set.of(), liquidFallingMaterial);
    }
    
    /** @return an updated vertical slice, equal to this slice with an additional layer of falling material settled, if possible */
    private VerticalSlice tick() {
        Set<Point> newSettled = new HashSet<>(settled);
        Set<Point> newPassedThrough = new HashSet<>(passedThrough);
        
        Queue<Point> tricklingFallingMaterial = new LinkedList<>();
        tricklingFallingMaterial.add(spring);
        
        while (!tricklingFallingMaterial.isEmpty()) {
            Point tricklingFallingMaterialPoint = tricklingFallingMaterial.poll();
            
            Point below = tricklingFallingMaterialPoint.belowNeighbour();
            if (maximumY < below.y()) {
                // Ignore. No need to trickle down further.
            } else if (solid.contains(below) || newSettled.contains(below)) {
                // The tile below is solid or settled. Unable to trickle down.
                
                // TODO take material type into account
                
                // Find out whether falling material can continue to trickle down on the left and/or right.
                Set<Point> visited = new HashSet<>();
                visited.add(tricklingFallingMaterialPoint);
                
                // Search to the left.
                Point left = tricklingFallingMaterialPoint.leftNeighbour();
                while(!solid.contains(left) && (solid.contains(left.belowNeighbour()) || newSettled.contains(left.belowNeighbour()))) {
                    visited.add(left);
                    left = left.leftNeighbour();
                }
                if (!solid.contains(left)) {
                    visited.add(left);
                }
                
                // Search to the right.
                Point right = tricklingFallingMaterialPoint.rightNeighbour();
                while(!solid.contains(right) && (solid.contains(right.belowNeighbour()) || newSettled.contains(right.belowNeighbour()))) {
                    visited.add(right);
                    right = right.rightNeighbour();
                }
                if (!solid.contains(right)) {
                    visited.add(right);
                }
                
                boolean settle = true;
                if (!(solid.contains(left.belowNeighbour()) || newSettled.contains(left.belowNeighbour()))) {
                    settle = false;
                    tricklingFallingMaterial.add(left);
                }
                if (!(solid.contains(right.belowNeighbour()) || newSettled.contains(right.belowNeighbour()))) {
                    settle = false;
                    tricklingFallingMaterial.add(right);
                }
                
                if (settle) {
                    // The falling material settles here.
                    newSettled.addAll(visited);
                } else {
                    // The falling material continues trickling on the left and/or right.
                    newPassedThrough.addAll(visited);
                }
                
            } else {
                // The tile below can be passed through.
                newPassedThrough.add(below);
                tricklingFallingMaterial.add(below);
            }
        }
        
        return new VerticalSlice(spring, solid, newSettled, newPassedThrough, liquidFallingMaterial);
    }
    
    /** @return this slice after falling material has settled wherever possible */
    public VerticalSlice tickUntilDone() {
        VerticalSlice previous = this;
        VerticalSlice current = this.tick();
        while (!previous.equals(current)) {
            previous = current;
            current = current.tick();
            LOGGER.debug("Current {}", current);
        }
        return current;
    }
    
    /** @return number of tiles where falling material has settled */
    public int countSettled() {
        return settled.size();
    }
    
    /** @return number of tiles reached by falling material, that is: the number of tiles where it is at rest + the number of tiles where it has passed through */
    public int countReached() {
        // Eliminate any duplicate tiles
        Set<Point> reached = new HashSet<>();
        reached.addAll(settled);
        reached.addAll(passedThrough);
        
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
                } else if (solid.contains(point)) {
                    c = '#';
                } else if (settled.contains(point)) {
                    c = '~';
                } else if (passedThrough.contains(point)) {
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
        return Objects.hash(solid, spring, settled, passedThrough);
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
        return Objects.equals(solid, other.solid) && Objects.equals(spring, other.spring)
                && Objects.equals(settled, other.settled) && Objects.equals(passedThrough, other.passedThrough);
    }
}
