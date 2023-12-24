package nl.mvdr.adventofcode.adventofcode2023.day24;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * A hailstone in two dimensions.
 * 
 * The third dimension (as in, the z axis) is entirely ignored for each hailstone.
 *
 * @author Martijn van de Rijdt
 */
record Hailstone(BigPoint location, BigPoint velocity) {
    
    /**
     * Parses a hailstone.
     * 
     * @param text string representation of a hailstone, for example: "19, 13, 30 @ -2,  1, -2"
     * @return hailstone
     */
    static Hailstone parse(String text) {
        var points = Stream.of(text.split(" +@ +"))
                .map(BigPoint::parse)
                .toList();
        if (points.size() != 2) {
            throw new IllegalArgumentException("Unable to parse as a hailstone: " + text);
        }
        return new Hailstone(points.getFirst(), points.getLast());
    }
    
    /**
     * Given another hailstone, determines where the paths of these hailstones will cross.
     * 
     * @param other other hailstone
     * @return intersection of the paths of the two hailstones; empty if they are equal or falling along parallel paths
     */
    Optional<BigPoint> findPathIntersection(Hailstone other) {
        return this.getPath().findPathIntersection(other.getPath())
                .filter(Predicate.not(this::isInPast))
                .filter(Predicate.not(other::isInPast));
    }
    
    /**
     * Checks whether the given point was visited in the past.
     * 
     * @param point a point; must be on this hailstone's path!
     * @return whether the point was visited in the past
     */
    private boolean isInPast(BigPoint point) {
        var difference = point.subtract(this.location);
        return velocity.x().signum() != difference.x().signum();
    }
    
    /** @return the path of this hailstone */
    private Line getPath() {
        return new Line(location, location.add(velocity));
    }
    
    @Override
    public String toString() {
        return location + " @ " + velocity;
    }
}