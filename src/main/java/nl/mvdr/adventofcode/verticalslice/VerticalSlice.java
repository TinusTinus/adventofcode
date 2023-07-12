package nl.mvdr.adventofcode.verticalslice;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.OptionalInt;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Stream;

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
    
    /** Maximum y coordinate from the scan input (that is, the solid material). */
    private final int maximumY;
    /** Y coordinate of the floor. */
    private final OptionalInt floorY;

    /**
     * Constructor.
     * 
     * @param spring the tile where falling material originates
     * @param solid the tiles containing solid material
     * @param settled the tiles where falling material has settled
     * @param passedThrough the tiles where falling material has passed through
     * @param liquidFallingMaterial whether the falling material is a liquid
     * @param floor whether there is a floor below the structure; only relevant / supported for solid falling materials
     */
    private VerticalSlice(Point spring, Set<Point> solid, Set<Point> settled, Set<Point> passedThrough, boolean liquidFallingMaterial, boolean floor) {
        super();
        this.spring = spring;
        this.solid = solid;
        this.settled = settled;
        this.passedThrough = passedThrough;
        this.liquidFallingMaterial = liquidFallingMaterial;
        
        int maximumSolidY = solid.stream()
                .mapToInt(Point::y)
                .max()
                .getAsInt();
        if (floor) {
            floorY = OptionalInt.of(maximumSolidY + 2);
        } else {
            floorY = OptionalInt.empty();
        }
        this.maximumY = floorY.orElse(maximumSolidY);
    }

    /**
     * Constructor.
     * 
     * @param spring the tile where falling material originates
     * @param clay the tiles containing solid material
     * @param liquidFallingMaterial whether the falling material is a liquid
     * @param floor whether there is a floor below the structure; only relevant / supported for solid falling materials
     */
    public VerticalSlice(Point spring, Set<Point> solid, boolean liquidFallingMaterial, boolean floor) {
        this(spring, solid, Set.of(), Set.of(), liquidFallingMaterial, floor);
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
            } else if (isSolid(below) || newSettled.contains(below)) {
                // The tile below is solid or settled. Unable to trickle down.
                
                boolean settle;
                // Find out whether falling material can continue to trickle down on the left and/or right.
                Set<Point> visited = new HashSet<>();
                visited.add(tricklingFallingMaterialPoint);

                if (liquidFallingMaterial) {
                    // Search to the left.
                    Point left = tricklingFallingMaterialPoint.leftNeighbour();
                    while(!isSolid(left) && (isSolid(left.belowNeighbour()) || newSettled.contains(left.belowNeighbour()))) {
                        visited.add(left);
                        left = left.leftNeighbour();
                    }
                    if (!isSolid(left)) {
                        visited.add(left);
                    }
                    
                    // Search to the right.
                    Point right = tricklingFallingMaterialPoint.rightNeighbour();
                    while(!isSolid(right) && (isSolid(right.belowNeighbour()) || newSettled.contains(right.belowNeighbour()))) {
                        visited.add(right);
                        right = right.rightNeighbour();
                    }
                    if (!isSolid(right)) {
                        visited.add(right);
                    }
                    
                    settle = true;
                    if (!(isSolid(left.belowNeighbour()) || newSettled.contains(left.belowNeighbour()))) {
                        settle = false;
                        tricklingFallingMaterial.add(left);
                    }
                    if (!(isSolid(right.belowNeighbour()) || newSettled.contains(right.belowNeighbour()))) {
                        settle = false;
                        tricklingFallingMaterial.add(right);
                    }
                } else {
                    Point downAndLeft = tricklingFallingMaterialPoint.leftNeighbour().belowNeighbour();
                    Point downAndRight = tricklingFallingMaterialPoint.rightNeighbour().belowNeighbour();
                    if (!isSolid(downAndLeft) && !newSettled.contains(downAndLeft)) {
                        // able to move down and left
                        visited.add(downAndLeft);
                        tricklingFallingMaterial.add(downAndLeft);
                        settle = false;
                    } else if (!isSolid(downAndRight) && !newSettled.contains(downAndRight)) {
                        // able to move down and right
                        visited.add(downAndRight);
                        tricklingFallingMaterial.add(downAndRight);
                        settle = false;
                    } else {
                        // settle here
                        settle = true;
                    }
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
        
        return new VerticalSlice(spring, solid, newSettled, newPassedThrough, liquidFallingMaterial, floorY.isPresent());
    }

    /**
     * Checks whether the given point is solid.
     * 
     * @param point point
     * @return whether the given point is a solid
     */
    private boolean isSolid(Point point) {
        return solid.contains(point)
                || (floorY.isPresent() && floorY.orElseThrow() == point.y());
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
        var minimumY = solid.stream()
                .mapToInt(Point::y)
                .min()
                .getAsInt();
        reached.removeIf(point -> point.y() < minimumY);
        reached.removeIf(point -> maximumY < point.y());
        
        return reached.size();
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("vertical slice:\n");
        var minimumYForPrint = Stream.concat(solid.stream(), settled.stream())
                .mapToInt(Point::y)
                .min()
                .getAsInt();
        Set<Point> points = new HashSet<>();
        points.add(spring);
        points.addAll(solid);
        points.addAll(settled);
        points.addAll(passedThrough);
        var minimumX = points.stream()
                .mapToInt(Point::x)
                .min()
                .getAsInt();
        var maximumX = points.stream()
                .mapToInt(Point::x)
                .max()
                .getAsInt();
        for (int y = minimumYForPrint; y != maximumY + 1; y++) {
            for (int x = minimumX; x != maximumX + 1; x++) {
                Point point = new Point(x, y);
                
                char c;
                if (spring.equals(point)) {
                    c = '+';
                } else if (isSolid(point)) {
                    c = '#';
                } else if (settled.contains(point) && liquidFallingMaterial) {
                    c = '~';
                } else if (settled.contains(point) && !liquidFallingMaterial) {
                    c = 'o';
                } else if (passedThrough.contains(point) && liquidFallingMaterial) {
                    c = '|';
                } else if (passedThrough.contains(point) && !liquidFallingMaterial) {
                    c = '~';
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
