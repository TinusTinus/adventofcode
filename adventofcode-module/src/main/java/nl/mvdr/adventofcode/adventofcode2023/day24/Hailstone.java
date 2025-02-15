package nl.mvdr.adventofcode.adventofcode2023.day24;

import java.util.stream.Stream;

/**
 * A hailstone in two dimensions.
 * 
 * The third dimension (as in, the z axis) is entirely ignored for each hailstone.
 *
 * @author Martijn van de Rijdt
 */
record Hailstone(BigPoint3D location, BigPoint3D velocity) {
    
    /**
     * Parses a hailstone.
     * 
     * @param text string representation of a hailstone, for example: "19, 13, 30 @ -2,  1, -2"
     * @return hailstone
     */
    static Hailstone parse(String text) {
        var points = Stream.of(text.split(" +@ +"))
                .map(BigPoint3D::parse)
                .toList();
        if (points.size() != 2) {
            throw new IllegalArgumentException("Unable to parse as a hailstone: " + text);
        }
        return new Hailstone(points.getFirst(), points.getLast());
    }

    /**
     * @return a two-dimensional view of this hailstone, without taking the z axis into consideration
     */
    Hailstone2D to2D() {
        return new Hailstone2D(location.ignoreZAxis(), velocity.ignoreZAxis());
    }
    
    @Override
    public String toString() {
        return location + " @ " + velocity;
    }
}
