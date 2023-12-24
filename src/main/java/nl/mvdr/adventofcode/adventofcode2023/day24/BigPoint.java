package nl.mvdr.adventofcode.adventofcode2023.day24;

import java.math.BigInteger;
import java.util.stream.Stream;

/**
 * A point in two dimensions, where each coordinate is a {@link BigInteger}.
 * 
 * Could represent a position in two-dimensional space,
 * or a velocity vector in two-dimensional space.
 *
 * @author Martijn van de Rijdt
 */
record BigPoint(BigInteger x, BigInteger y) {
    
    /**
     * Parses the string representation of a position or velocity of a hailstone.
     * 
     * If a three-dimensional value is passed into this method, the z axis is ignored entirely.
     * 
     * @param text a point in at least two dimensions, for example: "19, 13, 30"
     * @return the first two coordinates of the point
     */
    static BigPoint parse(String text) {
        var values = Stream.of(text.split(", "))
                .map(BigInteger::new)
                .limit(2L)
                .toList();
        return new BigPoint(values.getFirst(), values.getLast());
    }
    
    @Override
    public String toString() {
        return x + "," + y;
    }
}
