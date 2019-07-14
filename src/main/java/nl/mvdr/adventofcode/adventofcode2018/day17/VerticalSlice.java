package nl.mvdr.adventofcode.adventofcode2018.day17;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.annotation.processing.Generated;

import nl.mvdr.adventofcode.adventofcode2018.point.Point;

/**
 * A two-dimensional vertical slice of the ground.
 *
 * @author Martijn van de Rijdt
 */
class VerticalSlice {
    /** The square meter where water originates. */
    private final Point spring;
    
    /** The square meters where the ground is clay. */
    private final Set<Point> clay;
    
    /** The square meters where water has settled. */
    private final Set<Point> water;
    
    /** The square meters where water has passed through. */
    private final Set<Point> wetSand;

    /**
     * Constructor.
     * 
     * @param spring the quare meter where water originates
     * @param clay the square meters where the ground is clas
     * @param water the square meters where water has settled
     * @param wetSand the square meters where water has passed through
     */
    private VerticalSlice(Point spring, Set<Point> clay, Set<Point> water, Set<Point> wetSand) {
        super();
        this.spring = spring;
        this.clay = clay;
        this.water = water;
        this.wetSand = wetSand;
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
    
    /** @return minimum y coordinate from the scan input (that is, the clay) */
    private int minimumY() {
        return clay.stream()
                .mapToInt(Point::getY)
                .min()
                .getAsInt();
    }
    
    /** @return maximum y coordinate from the scan input (that is, the clay) */
    private int maximumY() {
        return clay.stream()
                .mapToInt(Point::getY)
                .max()
                .getAsInt();
    }
    
    /** @return minimum x coordinate containing anything other than sand */
    private int minimumX() {
        Set<Point> points = new HashSet<>();
        points.add(spring);
        points.addAll(clay);
        points.addAll(water);
        points.addAll(wetSand);
        
        return points.stream()
                .mapToInt(Point::getX)
                .min()
                .getAsInt();
    }
    
    /** @return maximum x coordinate containing anything other than sand */
    private int maximumX() {
        Set<Point> points = new HashSet<>();
        points.add(spring);
        points.addAll(clay);
        points.addAll(water);
        points.addAll(wetSand);
        
        return points.stream()
                .mapToInt(Point::getX)
                .max()
                .getAsInt();
    }

    /** @return an updated vertical slice, equal to this slice with an additional layer of water settled, if possible */
    private VerticalSlice tick() {
        return this; // TODO implement
    }
    
    /** @return this slice after water has settled wherever possible */
    VerticalSlice tickUntilDone() {
        VerticalSlice previous = this;
        VerticalSlice current = this.tick();
        while (!previous.equals(current)) {
            previous = current;
            current = current.tick();
        }
        return current;
    }
    
    /** @return number of tiles reached by water, that is: the number of tiles where water is at rest + the number of tiles where water has passed through */
    int reachedByWater() {
        return water.size() + wetSand.size();
    }
    
    @Override
    public String toString() {
        int minX = minimumX();
        int maxX = maximumX();
        int minY = minimumY();
        int maxY = maximumY();
        
        StringBuilder builder = new StringBuilder("Vertical slice:\n");
        for (int y = minY; y != maxY + 1; y++) {
            for (int x = minX; x != maxX + 1; x++) {
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
