package nl.mvdr.adventofcode.adventofcode2023.day24;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Stream;

/**
 * A point in three dimensions, where each coordinate is a {@link BigDecimal}.
 * 
 * Could represent a position or a velocity vector.
 *
 * @author Martijn van de Rijdt
 */
record BigPoint3D(BigDecimal x, BigDecimal y, BigDecimal z) {
    
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
                .map(BigDecimal::new)
                .map(value -> value.setScale(SCALE))
                .toList();
        if (values.size() != 3) {
            throw new IllegalArgumentException("Unable to parse: " + text);
        }
        return new BigPoint3D(values.getFirst(), values.get(1), values.getLast());
    }
    
    /**
     * Constructor.
     * 
     * @param x first coordinate value
     * @param y second coordinate value
     * @param z third coordinate value
     */
    BigPoint3D(BigDecimal x, BigDecimal y, BigDecimal z) {
        this.x = x.setScale(SCALE, RoundingMode.HALF_UP);
        this.y = y.setScale(SCALE, RoundingMode.HALF_UP);
        this.z = z.setScale(SCALE, RoundingMode.HALF_UP);
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
