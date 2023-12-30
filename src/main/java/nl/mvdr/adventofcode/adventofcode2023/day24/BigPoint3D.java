package nl.mvdr.adventofcode.adventofcode2023.day24;

import java.util.stream.Stream;

/**
 * A point in three dimensions, where each coordinate is a {@link long}.
 * 
 * Could represent a position or a velocity vector.
 *
 * @author Martijn van de Rijdt
 */
record BigPoint3D(long x, long y, long z) {
    
    static final int SCALE = 3;
    
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
    
    @Override
    public String toString() {
        return x + "," + y + ", " + z;
    }
}
