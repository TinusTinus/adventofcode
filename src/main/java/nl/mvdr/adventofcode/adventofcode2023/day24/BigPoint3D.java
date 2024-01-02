package nl.mvdr.adventofcode.adventofcode2023.day24;

import java.util.stream.Stream;

import nl.mvdr.adventofcode.point.Axis3D;

/**
 * A point in three dimensions, where each coordinate is a {@link long}.
 * 
 * Could represent a position or a velocity vector.
 *
 * @author Martijn van de Rijdt
 */
record BigPoint3D(long x, long y, long z) {
    
    /**
     * Parses the string representation of a position or velocity of a hailstone.
     * 
     * If a three-dimensional value is passed into this method, the z axis is ignored entirely.
     * 
     * @param text a point in at least two dimensions, for example: "19, 13, 30"
     * @return the first two coordinates of the point
     */
    static BigPoint3D parse(String text) {
        var values = Stream.of(text.split(", +"))
                .map(Long::valueOf)
                .toList();
        if (values.size() != 3) {
            throw new IllegalArgumentException("Unable to parse: " + text);
        }
        return new BigPoint3D(values.getFirst().longValue(), values.get(1).longValue(), values.getLast().longValue());
    }
    
    /**
     * @return this point in two dimensions, where the z axis is ignored entirely
     */
    BigPoint2D ignoreZAxis() {
        return new BigPoint2D(x, y);
    }
    
    /**
     * Gets the value for the given axis.
     * 
     * @param axis 3D axis
     * @return coordinate value
     */
    long get(Axis3D axis) {
        return switch(axis) {
            case X -> x;
            case Y -> y;
            case Z -> z;
            default -> throw new IllegalArgumentException("Unexpected axis: " + axis);
        };
    }
    
    @Override
    public String toString() {
        return x + "," + y + ", " + z;
    }
}
