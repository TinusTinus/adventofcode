package nl.mvdr.adventofcode.adventofcode2023.day24;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * A point in two dimensions, where each coordinate is a {@link BigDecimal}.
 * 
 * Could represent a position in two-dimensional space,
 * or a velocity vector in two-dimensional space.
 *
 * @author Martijn van de Rijdt
 */
record BigPoint2D(BigDecimal x, BigDecimal y) {
    
    static final int SCALE = 3;
    
    /**
     * Constructor.
     * 
     * @param x first coordinate value
     * @param y second coordinate value
     */
    BigPoint2D(BigDecimal x, BigDecimal y) {
        this.x = x.setScale(SCALE, RoundingMode.HALF_UP);
        this.y = y.setScale(SCALE, RoundingMode.HALF_UP);
    }
    
    /**
     * Constructor.
     * 
     * @param x first coordinate value
     * @param y second coordinate value
     */
    BigPoint2D(double x, double y) {
        this(new BigDecimal(x), new BigDecimal(y));
    }
    
    /**
     * Adds the given other point by adding the coordinate values together.
     * 
     * @param other other point
     * @return sum
     */
    BigPoint2D add(BigPoint2D other) {
        var newX = x.add(other.x());
        var newY = y.add(other.y());
        return new BigPoint2D(newX, newY);
    }
    
    /**
     * Subtracts the given other point from this one.
     * 
     * @param other other point
     * @return result of subtraction
     */
    BigPoint2D subtract(BigPoint2D other) {
        return add(new BigPoint2D(other.x().negate(), other.y.negate()));
    }
    
    @Override
    public String toString() {
        return x + "," + y;
    }
}
