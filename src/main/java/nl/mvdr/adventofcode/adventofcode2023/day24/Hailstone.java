package nl.mvdr.adventofcode.adventofcode2023.day24;

import java.util.Optional;
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
        var points = Stream.of(text.split(" @ "))
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
        return null; // TODO implement
    }
    
    @Override
    public String toString() {
        return location + " @ " + velocity;
    }
}
